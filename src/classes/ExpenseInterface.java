package classes;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseInterface {
        void addExpense(LocalDate date, int amount, String description, Category category, ExpenseCounter counter);
        void updateExpense(int expenseId, LocalDate date, int amount, String description, Category category);
        void deleteExpense(int expenseId);
        List<Expense> getAllExpenses();
        Expense getExpenseById(int expenseId);
}
