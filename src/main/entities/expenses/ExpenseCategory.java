package main.entities.expenses;
import main.interfaces.transaction.TransactionCategory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ExpenseCategory implements TransactionCategory {
    public static final Set<ExpenseCategory> CATEGORIES;

    static {
        Set<ExpenseCategory> categories = new HashSet<>();
        categories.add(new ExpenseCategory("Groceries"));
        categories.add(new ExpenseCategory("Rent"));
        categories.add(new ExpenseCategory("Utilities"));
        categories.add(new ExpenseCategory("Transportation"));
        categories.add(new ExpenseCategory("Dining Out"));
        categories.add(new ExpenseCategory("Entertainment"));
        categories.add(new ExpenseCategory("Clothing"));
        categories.add(new ExpenseCategory("Healthcare"));
        categories.add(new ExpenseCategory("Education"));
        categories.add(new ExpenseCategory("Travel"));
        categories.add(new ExpenseCategory("Insurance"));
        categories.add(new ExpenseCategory("Savings"));
        categories.add(new ExpenseCategory("Gifts and Donations"));
        categories.add(new ExpenseCategory("Personal Care"));
        categories.add(new ExpenseCategory("Home Improvement"));
        categories.add(new ExpenseCategory("Electronics"));
        categories.add(new ExpenseCategory("Hobbies"));
        categories.add(new ExpenseCategory("Pet Care"));
        categories.add(new ExpenseCategory("Taxes"));
        categories.add(new ExpenseCategory("Miscellaneous"));
        CATEGORIES = Collections.unmodifiableSet(categories);
    }

    private String name;

    public ExpenseCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseCategory that = (ExpenseCategory) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

