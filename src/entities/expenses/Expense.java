package entities.expenses;


import entities.transactions.Transaction;
import entities.user.User;
import exceptions.InvalidExpenseException;

public class Expense extends Transaction {
    private static int globalIdCounter = 1;
    private ExpenseCategory category;

    public Expense() {
        this.setId(generateUniqueId());
    }

    public Expense(User user,Double amount, ExpenseCategory category, String date) {

        if (amount <= 0) {
            throw new InvalidExpenseException("Expense amount must be positive");
        }

        this.setId(generateUniqueId());
        this.setAmount(amount);
        this.setCategory(category);
        this.setDate(date);
        this.setUser(user);
    }

    public ExpenseCategory getCategory() {
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