package io.muenchendigital.digiwf.alw.integration.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Identification data for the specific case
 * of an organisational responsibility request.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AlwZustaendigkeitRequest {

    /**
     * The AZR number.
     */
    private String azrNummer;

}