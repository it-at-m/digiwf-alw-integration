package io.muenchendigital.digiwf.alw.integration.domain.exception;

// TODO: needed?
public class AlwException extends Exception {

    public AlwException(final String message, final Exception exception) {
        super(message, exception);
    }

    public AlwException(final String message) {
        super(message);
    }

}
