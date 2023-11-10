package interfaces.expense;

import exception.InvalidExpenseException;
import interfaces.expense.ExpenseAmountValidator;

public class ExpenseAmountValidatorImpl implements ExpenseAmountValidator {
    @Override
    public boolean notValidAmount(double amount) throws InvalidExpenseException {
        if (amount < 0) {
            throw new InvalidExpenseException("El monto debe ser mayor a cero");
        }
        return false;
    }
}
