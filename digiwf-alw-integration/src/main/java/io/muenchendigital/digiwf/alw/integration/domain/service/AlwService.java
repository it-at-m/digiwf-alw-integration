package io.muenchendigital.digiwf.alw.integration.domain.service;

import io.muenchendigital.digiwf.alw.integration.domain.exception.AlwException;
import io.muenchendigital.digiwf.alw.integration.domain.model.AlwZustaendigkeitRequest;
import io.muenchendigital.digiwf.alw.integration.domain.model.AlwZustaendigkeitResponse;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.infrastructure.RoutingCallback;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.service.CorrelateMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class AlwService {

    private final String url;

    /**
     * TODO.
     *
     * @param alwZustaendigkeitRequest TODO
     */
    public AlwZustaendigkeitResponse getZustaendigkeit(final AlwZustaendigkeitRequest alwZustaendigkeitRequest) throws AlwException {

        log.info("Connect to {} for azr: {}", url, alwZustaendigkeitRequest.getAzrNummer());

        CorrelateMessageService r;
        return new AlwZustaendigkeitResponse("KVR");
    }

}
