package main.dao;

import main.dao.dto.ExpenseDto;
import main.entities.expenses.Expense;
import main.exceptions.DAOException;

import java.util.List;

public interface ExpenseDao {
    ExpenseDto getById(int id, int requestingUserId) throws DAOException;

    List<ExpenseDto> getAll() throws DAOException;
    void insert(Expense expense) throws DAOException;
    void update(ExpenseDto expense) throws DAOException;
    void delete(int id) throws DAOException;
}