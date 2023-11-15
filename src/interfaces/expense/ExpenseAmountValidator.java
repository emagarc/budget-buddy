package interfaces.expense;

import exceptions.InvalidExpenseException;

@FunctionalInterface
public interface ExpenseAmountValidator {
    boolean notValidAmount(double amount) throws InvalidExpenseException;
}
