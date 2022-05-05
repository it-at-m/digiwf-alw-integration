package io.muenchendigital.digiwf.alw.integration.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "digiwf.alw")
public class AlwProperties {

    private String url = "http://alweai.muenchen.de";

}
