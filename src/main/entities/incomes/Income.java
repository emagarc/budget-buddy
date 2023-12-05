package main.entities.incomes;

import main.entities.transactions.Transaction;
import main.entities.user.User;


public class Income extends Transaction {
    private int userId;
    private double amount;
    private int categoryId;
    private String date;

    public Income () {
    }

    public Income(int userId, double amount, int categoryId, String date) {
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + this.getId() +
                ", amount=" + this.getAmount() +
                ", category=" + this.getCategory() +
                ", date='" + this.getDate() + '\'' +
                '}';
    }
}
