package main.interfaces.expense;

import main.exceptions.InvalidExpenseException;

public class ExpenseAmountValidatorImpl implements ExpenseAmountValidator {
    @Override
    public boolean validateAmount(double amount) throws InvalidExpenseException {
        if (amount < 0) {
            throw new InvalidExpenseException("El monto debe ser mayor a cero");
        }
        return true;
    }
}
