package main.dao;

import main.entities.expenses.ExpenseCategory;

public interface ExpenseCategoryDao {
    ExpenseCategory getCategoryById(int categoryId);
}
