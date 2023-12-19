package main.entities.expenses;

import main.interfaces.transaction.TransactionCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseCategorySelector {
    private final List<ExpenseCategory> categories;

    public ExpenseCategorySelector() {
        this.categories = new ArrayList<>(ExpenseCategory.CATEGORIES);
    }

    public int selectCategory() {
        displayCategories();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la categoría del gasto: ");
        String categoryName = scanner.nextLine().trim();

        ExpenseCategory selectedCategory = findCategoryByName(categoryName);
        if (selectedCategory != null) {
            return selectedCategory.getId();
        } else {
            System.out.println("Categoría no válida.");
            return -1; // O manejar el error de alguna manera
        }
    }

    private ExpenseCategory findCategoryByName(String categoryName) {
        for (ExpenseCategory category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
    }

    private void displayCategories() {
        System.out.println("Lista de categorías de gastos:");
        for (ExpenseCategory category : categories) {
            System.out.println(category.getId() + ". " + category.getName());
        }
    }
}