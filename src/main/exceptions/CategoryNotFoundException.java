package main.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "No data available for the specified category.";

    public CategoryNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CategoryNotFoundException(String customMessage) {
        super(customMessage);
    }
}
