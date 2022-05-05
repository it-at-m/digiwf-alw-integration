package io.muenchendigital.digiwf.alw.integration.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Organisational responsibility data for the specific case.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AlwZustaendigkeitResponse {

    /**
     * Responsible organisational unit.
     */
    private String zustaendigeGruppe;

}