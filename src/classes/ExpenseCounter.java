package classes;

public class ExpenseCounter {
    private int currentId;

    public ExpenseCounter() {
        currentId = 0;
    }

    public int getNextId() {
        return currentId++;
    }
}
