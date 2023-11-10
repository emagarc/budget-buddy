package entities.income;

import entities.user.User;
import interfaces.income.IncomeSource;

public class Income implements IncomeSource {
    private static int globalIdCounter = 1;
    private Integer id;
    private Double amount;
    private IncomeCategory category;
    private String date;
    private User user;

    public Income () {
        this.id = generateUniqueId();
    }

    public Income(Double amount, IncomeCategory category, String date, User user) {
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

    public IncomeCategory getCategory() {
        return category;
    }

    public void setCategory(IncomeCategory category) {
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
