package io.muenchendigital.digiwf.alw.integration.domain.exception;

public class MissingInformationMailException extends Exception {

    public MissingInformationMailException(final String message, final Exception exception) {
        super(message, exception);
    }

    public MissingInformationMailException(final String message) {
        super(message);
    }

}
