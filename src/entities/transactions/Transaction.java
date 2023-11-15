package entities.transactions;

import entities.user.User;
import interfaces.transaction.TransactionCategory;
import interfaces.transaction.TransactionRelatedObject;

public class Transaction implements TransactionRelatedObject {
    private static int globalIdCounter = 1;
    private Integer id;
    private Double amount;
    private TransactionCategory category;
    private String date;
    private User user;

    public Transaction() {
        this.id = generateUniqueId();
    }

    public Transaction(Integer id, Double amount, TransactionCategory category, String date, User user) {
        this.id = generateUniqueId();
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.user = user;
    }

    public static int getGlobalIdCounter() {
        return globalIdCounter;
    }

    public static void setGlobalIdCounter(int globalIdCounter) {
        Transaction.globalIdCounter = globalIdCounter;
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

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
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
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", category=" + category +
                ", date='" + date + '\'' +
                '}';
    }
}
