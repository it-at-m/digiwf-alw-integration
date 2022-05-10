/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2020
 */
package io.muenchendigital.digiwf.alw.integration.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;


@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = {"io.muenchendigital.digiwf.alw.integration"})
@EnableConfigurationProperties({AlwPersoneninfoProperties.class})
public class AlwAutoConfiguration {

    private final AlwPersoneninfoProperties alwPersoneninfoProperties;

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        final HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(alwPersoneninfoProperties.getTimeout());
        final RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor(alwPersoneninfoProperties.getUsername(), alwPersoneninfoProperties.getPassword()));
        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public AlwPersoneninfoConfig alwConfig() {
        return new AlwPersoneninfoConfig(
                alwPersoneninfoProperties.getBaseurl(),
                alwPersoneninfoProperties.getRestEndpoint(),
                alwPersoneninfoProperties.getTimeout()
        );
    }

}
