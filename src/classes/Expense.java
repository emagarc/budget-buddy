package classes;
import java.time.LocalDate;
import java.util.List;

public class Expense implements  ExpenseInterface {
    private LocalDate date;
    private int amount;
    private String description;
    private Category category;
    private int id;

    public Expense(LocalDate date, int amount, String description, Category category, ExpenseCounter counter) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.id = counter.getNextId();
    }

    // Implementación de los métodos de ExpenseInterface

    @Override
    public void addExpense(LocalDate date, int amount, String description, Category  category, ExpenseCounter counter) {
        // Implementa la lógica para agregar un gasto aquí
        // Puedes utilizar el contador para generar el ID del gasto
    };

    @Override
    public void updateExpense(int expenseId, LocalDate date, int amount, String description, Category category){
        // Implementa la lógica para actualizar un gasto aquí
    };

    @Override
    public void deleteExpense(int expenseId) {
        // Implementa la lógica para eliminar un gasto aquí
    }

    @Override
    public List<Expense> getAllExpenses() {
        // Implementa la lógica para obtener todos los gastos aquí
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        // Implementa la lógica para obtener un gasto por ID aquí
        return null; // Debes reemplazar esto con la implementación real
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}