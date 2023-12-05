package main.dao;

import main.entities.expenses.ExpenseCategory;
import main.exceptions.DAOException;

public interface ExpenseCategoryDao {
    ExpenseCategory getCategoryById(int categoryId) throws DAOException;
}
