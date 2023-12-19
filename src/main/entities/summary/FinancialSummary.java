package main.entities.summary;

import main.entities.expenses.Expense;
import main.entities.incomes.Income;
import main.entities.transactions.Transaction;
import main.entities.user.User;
import main.exceptions.NoDataException;

import java.time.LocalDate;
import java.util.ArrayList;
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

        if (expenses.isEmpty() && incomes.isEmpty()) {
            throw new NoDataException("No data available to calculate financial summary.");
        }

        totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        balance = totalIncome - totalExpenses;
    }

    public List<Transaction> getTransactionsByMonth(int year, int month) {
        List<Expense> expenses = user.getExpenses().stream()
                .filter(expense -> {
                    LocalDate expenseDate = LocalDate.parse(expense.getDate());
                    return expenseDate.getYear() == year && expenseDate.getMonthValue() == month;
                })
                .toList();

        List<Income> incomes = user.getIncomes().stream()
                .filter(income -> {
                    LocalDate incomeDate = LocalDate.parse(income.getDate());
                    return incomeDate.getYear() == year && incomeDate.getMonthValue() == month;
                })
                .toList();

        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(expenses);
        transactions.addAll(incomes);

        if (transactions.isEmpty()) {
            throw new NoDataException("No transactions available for the specified year and month.");
        }

        return transactions;
    }

    public double getProjectedBalanceForNextMonth() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();

        List<Transaction> transactions = getTransactionsByMonth(currentYear, currentMonth);

        if (transactions.isEmpty()) {
            throw new NoDataException("No transactions available to project balance for the next month.");
        }

        double projectedIncome = transactions.stream()
                .filter(transaction -> transaction instanceof Income)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double projectedExpenses = transactions.stream()
                .filter(transaction -> transaction instanceof Expense)
                .mapToDouble(Transaction::getAmount)
                .sum();

        return balance + projectedIncome - projectedExpenses;
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
        calculateFinancialSummary();
    }
}
