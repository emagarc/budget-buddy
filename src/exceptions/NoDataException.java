package exceptions;

public class NoDataException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "There is not enough data to implement this method";

    public NoDataException() {
        super(DEFAULT_MESSAGE);
    }

    public NoDataException(String customMessage) {
        super(customMessage);
    }
}
