package entities.expense;


import entities.transaction.Transaction;
import entities.user.User;
import exception.InvalidExpenseException;

public class Expense extends Transaction {
    private static int globalIdCounter = 1;
    private Integer id;
    private Double amount;
    private ExpenseCategory category;
    private String date;
    private User user;

    public Expense() {
        this.id = generateUniqueId();
    }

    public Expense(User user,Double amount, ExpenseCategory category, String date) {

        if (amount <= 0) {
            throw new InvalidExpenseException("Expense amount must be positive");
        }

        this.id = generateUniqueId();
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private static synchronized int generateUniqueId() {
        return globalIdCounter++;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", category=" + category +
                ", date='" + date + '\'' +
                '}';
    }
}