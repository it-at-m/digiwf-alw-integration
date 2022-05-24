/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2020
 */
package io.muenchendigital.digiwf.alw.integration.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = {"io.muenchendigital.digiwf.alw.integration"})
@EnableConfigurationProperties({AlwPersoneninfoProperties.class})
public class AlwAutoConfiguration {

    private final AlwPersoneninfoProperties alwPersoneninfoProperties;

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .basicAuthentication(alwPersoneninfoProperties.getUsername(), alwPersoneninfoProperties.getPassword(), Charset.defaultCharset())
                .setConnectTimeout(Duration.of(alwPersoneninfoProperties.getTimeout(), ChronoUnit.MILLIS))
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public AlwPersoneninfoConfig alwConfig() {
        return new AlwPersoneninfoConfig(
                alwPersoneninfoProperties.getBaseurl(),
                alwPersoneninfoProperties.getRestEndpoint()
        );
    }

}
