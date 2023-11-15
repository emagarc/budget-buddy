package entities.incomes;

import entities.transactions.Transaction;
import entities.user.User;


public class Income extends Transaction {
    private static int globalIdCounter = 1;

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
