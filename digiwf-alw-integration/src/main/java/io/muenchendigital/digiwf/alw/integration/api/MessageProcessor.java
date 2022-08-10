/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2020
 */
package io.muenchendigital.digiwf.alw.integration.api;

import io.muenchendigital.digiwf.alw.integration.domain.model.AlwPersoneninfoRequest;
import io.muenchendigital.digiwf.alw.integration.domain.model.AlwPersoneninfoResponse;
import io.muenchendigital.digiwf.alw.integration.domain.service.AlwPersoneninfoService;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.infrastructure.RoutingCallback;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.bpmnerror.service.BpmnErrorService;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.message.service.CorrelateMessageService;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.incident.service.IncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.context.MessageRoutingCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProcessor {

    public static final String TYPE_HEADER_GET_ALW_ZUSTAENDIGKEIT_EVENT_BUS = "getAlwZustaendigkeitEventBus";
    private final AlwPersoneninfoService alwPersoneninfoService;
    private final CorrelateMessageService correlateMessageService;
    private final BpmnErrorService bpmnErrorService;
    private final IncidentService incidentService;
    private static final String ALW_ZUSTAENDIGE_GRUPPE = "alwZustaendigeGruppe";

    /**
     * Override the custom router of the digiwf-spring-cloudstream-utils. We only have one type we need to map.
     * @return the custom router
     */
    @Bean
    public MessageRoutingCallback customRouter() {
        final Map<String, String> typeMappings = new HashMap<>();
        typeMappings.put(TYPE_HEADER_GET_ALW_ZUSTAENDIGKEIT_EVENT_BUS, TYPE_HEADER_GET_ALW_ZUSTAENDIGKEIT_EVENT_BUS);
        return new RoutingCallback(typeMappings);
    }

    /**
     * All messages from the route "getAlwZustaendigkeitEventBus" go here.
     *
     * @return the consumer
     */
    @Bean
    public Consumer<Message<AlwPersoneninfoRequest>> getAlwZustaendigkeitEventBus() {
        return message -> {
            log.info("Processing new request from eventbus");
            final AlwPersoneninfoRequest alwPersoneninfoRequest = message.getPayload();
            log.debug("Request: {}", alwPersoneninfoRequest);
            try {
                final AlwPersoneninfoResponse response = alwPersoneninfoService.getZustaendigkeit(alwPersoneninfoRequest);
                emitResponse(message.getHeaders(), response);
            } catch (final Exception e) {
                log.error("Request could not be fulfilled: {}", e.getMessage());
                emitIncident(message.getHeaders(), "Call to alwPersoneninfoService failed: " + e.getMessage());
            }
        };
    }

    /**
     * Function to emit a reponse using the correlateMessageService of digiwf-spring-cloudstream-utils
     *
     * @param messageHeaders          The MessageHeaders of the incoming message you want to correlate your answer to
     * @param alwPersoneninfoResponse the responsibility info
     */
    public void emitResponse(final MessageHeaders messageHeaders, final AlwPersoneninfoResponse alwPersoneninfoResponse) {
        final Map<String, Object> correlatePayload = new HashMap<>();
        correlatePayload.put(ALW_ZUSTAENDIGE_GRUPPE, alwPersoneninfoResponse.getZustaendigeGruppe());
        correlateMessageService.sendCorrelateMessage(messageHeaders, correlatePayload);
    }

    /**
     * Function to emit a reponse using the correlateMessageService of digiwf-spring-cloudstream-utils
     *
     * @param messageHeaders          The MessageHeaders of the incoming message you want to correlate your answer to
     */
    public void emitBpmnError(final MessageHeaders messageHeaders, final String errorCode, final String errorMessage) {
        bpmnErrorService.sendBpmnError(messageHeaders, errorCode, errorMessage);
    }

    public void emitIncident(final MessageHeaders messageHeaders, final String errorMessage) {
        incidentService.sendIncident(messageHeaders, errorMessage);
    }

}
