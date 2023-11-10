package entities.summary;

import entities.expense.Expense;
import entities.income.Income;
import entities.user.User;

import java.util.List;

public class FinancialSummary {
    private double totalIncome;
    private double totalExpenses;
    private double balance;
    private User user;

    public FinancialSummary(User user) {
        this.user = user;
        calculateFinancialSummary();
    }

    private void calculateFinancialSummary() {
        List<Expense> expenses = user.getExpenses();
        List<Income> incomes = user.getIncomes();
        totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        balance = totalIncome - totalExpenses;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
