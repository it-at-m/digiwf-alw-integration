package io.muenchendigital.digiwf.alw.integration.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "digiwf.alw.personeninfo")
public class AlwProperties {

    private String host;
    private Integer port;
    private String restendpoint;
    private Long timeout;
    private String basicauth_username;
    private String basicauth_password;
}
