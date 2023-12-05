package main.exceptions;

public class InsufficientDataException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Insufficient data.";

    public InsufficientDataException() {
        super(DEFAULT_MESSAGE);
    }

    public InsufficientDataException(String customMessage) {
        super(customMessage);
    }
}