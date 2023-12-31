package main.entities.user;

import main.entities.expenses.Expense;
import main.entities.incomes.Income;
import main.entities.transactions.Transaction;
import main.interfaces.transaction.TransactionCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private String userName;
    private String email;
    private String password;
    private List<Expense> expenses;
    private List<Income> incomes;

    public User() {
        expenses = new ArrayList<>();
        incomes = new ArrayList<>();
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        expenses = new ArrayList<>();
        incomes = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void removeExpense(Expense expense) { expenses.remove(expense); }

    public void addIncome(Income income) { incomes.add(income); }

    public void removeIncome(Income income) { incomes.remove(income); }

    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(expenses);
        transactions.addAll(incomes);
        return transactions;
    }

    public List<Transaction> getTransactionByCategory(TransactionCategory category) {
        return getTransactions().stream()
                .filter(transaction -> transaction.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", expenses=" + expenses +
                ", incomes=" + incomes +
                '}';
    }

}
