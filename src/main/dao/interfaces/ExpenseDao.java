package main.dao.interfaces;

import main.dao.dto.ExpenseDto;
import main.dao.dto.UserDto;
import main.entities.expenses.Expense;
import main.exceptions.DAOException;

import java.util.List;

public interface ExpenseDao {
    ExpenseDto getById(int id, UserDto requestingUser) throws DAOException;
    ExpenseDto getByIdForAdmin(int id) throws DAOException;
    List<ExpenseDto> getAll() throws DAOException;
    void insert(Expense expense) throws DAOException;
    void update(ExpenseDto expense) throws DAOException;
    void delete(int id) throws DAOException;
    List<ExpenseDto> getUserExpenses(UserDto userDto) throws DAOException;
}