package exception;

public class ExpenseCreationException extends RuntimeException {
    public ExpenseCreationException(String message) {
        super(message);
    }
}
