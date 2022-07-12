/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2020
 */
package io.muenchendigital.digiwf.alw.integration.api;

import io.muenchendigital.digiwf.alw.integration.domain.model.AlwPersoneninfoRequest;
import io.muenchendigital.digiwf.alw.integration.domain.model.AlwPersoneninfoResponse;
import io.muenchendigital.digiwf.alw.integration.domain.service.AlwPersoneninfoService;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.dto.ErrorDto;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.infrastructure.RoutingCallback;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.service.CorrelateMessageService;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.service.ErrorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.context.MessageRoutingCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProcessor { // TODO remove

    public static final String TYPE_HEADER_GET_ALW_ZUSTAENDIGKEIT_EVENT_BUS = "getAlwZustaendigkeitEventBus";
    private final AlwPersoneninfoService alwPersoneninfoService;
    private final CorrelateMessageService correlateMessageService;
    private final ErrorService errorService;
    private static final String ALW_ZUSTAENDIGE_GRUPPE = "alwZustaendigeGruppe";
    private final Sinks.Many<Message<ErrorDto>> errorSink;

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
//                emitResponse(message.getHeaders(), response);
                throw new RuntimeException("myexception"); // TODO
            } catch (final Exception e) {
                log.error("Request could not be fulfilled: {}", e.getMessage());
                emitError(message.getHeaders(), "999", "TODO");
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
    public void emitError(final MessageHeaders messageHeaders, final String errorCode, final String errorMessage) {
        errorService.sendError(messageHeaders, errorCode, errorMessage);
    }
}
