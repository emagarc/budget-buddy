package main.dao;

import main.entities.incomes.IncomeCategory;
import main.exceptions.DAOException;

public interface IncomeCategoryDao {
    IncomeCategory getCategoryById(int categoryId) throws DAOException;
}
