package main.entities.incomes;

import main.entities.transactions.Transaction;
import main.entities.user.User;


public class Income extends Transaction {
    private static int globalIdCounter = 1;

    private IncomeCategory category;

    public Income () {
        this.setId(generateUniqueId());
    }

    public Income(Double amount, IncomeCategory category, String date, User user) {
        this.setId(generateUniqueId());
        this.setAmount(amount);
        this.setCategory(category);
        this.setDate(date);
        this.setUser(user);
    }

    public IncomeCategory getCategory() {
        return category;
    }

    private static synchronized int generateUniqueId() {
        return globalIdCounter++;
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
