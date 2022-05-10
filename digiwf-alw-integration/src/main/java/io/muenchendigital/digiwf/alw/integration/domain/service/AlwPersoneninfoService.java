/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2020
 */
package io.muenchendigital.digiwf.alw.integration.domain.service;

import io.muenchendigital.digiwf.alw.integration.configuration.AlwPersoneninfoConfig;
import io.muenchendigital.digiwf.alw.integration.domain.exception.AlwException;
import io.muenchendigital.digiwf.alw.integration.domain.mapper.AlwPersoneninfoResponseMapper;
import io.muenchendigital.digiwf.alw.integration.domain.model.AlwPersoneninfoRequest;
import io.muenchendigital.digiwf.alw.integration.domain.model.AlwPersoneninfoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class AlwPersoneninfoService {

    private final AlwPersoneninfoConfig alwPersoneninfoConfig;

    private final RestTemplate restTemplate;

    private final AzrNumberValidator azrNumberValidator;

    private final AlwPersoneninfoResponseMapper mapper;

    /**
     * Determine organisational responsibility for a specific case.
     *
     * @param alwPersoneninfoRequest identification data of the specific case
     */
    public AlwPersoneninfoResponse getZustaendigkeit(final AlwPersoneninfoRequest alwPersoneninfoRequest) throws AlwException {
        azrNumberValidator.validate(alwPersoneninfoRequest.getAzrNummer());
        final String url = createAlwUrl(alwPersoneninfoRequest.getAzrNummer());
        log.info("Connecting to {} for personeninfo request", url);
        final String restResponse = performRestCall(url);
        log.debug("Response from AlwPersoneninfo: {}", restResponse);
        return mapper.map(restResponse);
    }

    private String performRestCall(final String url) throws AlwException {
        final String restResponse;
        try {
            restResponse = this.restTemplate.getForObject(url, String.class);
        } catch (final Exception ex) {
            throw new AlwException("Call to " + url + " failed", ex);
        }
        return restResponse;
    }

    private String createAlwUrl(final String azrNummer) {
        return String.format("%s%s%s", alwPersoneninfoConfig.getBaseurl(), alwPersoneninfoConfig.getRestEndpoint(), azrNummer);
    }

}
