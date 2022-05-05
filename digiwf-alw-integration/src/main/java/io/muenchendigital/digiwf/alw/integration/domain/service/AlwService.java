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

    private final String host;
    private final Integer port;
    private final String restEndpoint;
    private final Long timeout;
    private final String basicAuthUsername;
    private final String basicAuthPassword;

    /**
     * Determine organisational responsibility for a specific case.
     *
     * @param alwZustaendigkeitRequest identification data of the specific case
     */
    public AlwZustaendigkeitResponse getZustaendigkeit(final AlwZustaendigkeitRequest alwZustaendigkeitRequest) throws AlwException {

        log.info("Connect to {}:{} for azr: {}", host, port, alwZustaendigkeitRequest.getAzrNummer());

        return new AlwZustaendigkeitResponse("KVR"); // TODO: integrate eai-kvr
    }

}
