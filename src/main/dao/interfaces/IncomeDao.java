package main.dao.interfaces;

import main.dao.dto.IncomeDto;
import main.dao.dto.UserDto;
import main.entities.incomes.Income;
import main.entities.user.User;
import main.exceptions.DAOException;

import java.util.List;

public interface IncomeDao {
    IncomeDto getById(int id, UserDto requestingUser) throws DAOException;
    IncomeDto getByIdForAdmin(int id) throws DAOException;
    List<IncomeDto> getAll() throws DAOException;
    void insert(Income income) throws DAOException;
    void update(IncomeDto income) throws DAOException;
    void delete(IncomeDto incomeDto) throws DAOException;
    List<IncomeDto> getUserIncomes(UserDto userDto) throws DAOException;
}