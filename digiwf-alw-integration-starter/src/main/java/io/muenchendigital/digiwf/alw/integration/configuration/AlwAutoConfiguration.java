package io.muenchendigital.digiwf.alw.integration.configuration;

import io.muenchendigital.digiwf.alw.integration.domain.service.AlwService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = {"io.muenchendigital.digiwf.alw.integration"})
@EnableConfigurationProperties({AlwProperties.class})
public class AlwAutoConfiguration {

    private final AlwProperties alwProperties;

    /**
     * Configures the {@link AlwService}
     *
     * @return configured AlwService
     */
    @Bean
    @ConditionalOnMissingBean
    public AlwService getAlwService() {
        return new AlwService(alwProperties.getUrl());
    }
}
