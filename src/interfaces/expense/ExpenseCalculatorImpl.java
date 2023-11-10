package interfaces.expense;

import entities.expense.Expense;
import interfaces.expense.ExpenseCalculator;

public class ExpenseCalculatorImpl implements ExpenseCalculator {
    @Override
    public double calculateExpense(Expense expense) {
        return expense.getAmount();
    }

    @Override
    public double calculateTotalExpense(Expense[] expenses) {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            totalExpense += expense.getAmount();
        }
        return totalExpense;
    }
}
