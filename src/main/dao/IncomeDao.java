package main.dao;

import main.dao.dto.IncomeDto;
import main.exceptions.DAOException;

import java.util.List;

public interface IncomeDao {
    IncomeDto getById(int id, int requestingUserId) throws DAOException;

    List<IncomeDto> getAll() throws DAOException;
    void insert(IncomeDto expenseDto) throws DAOException;
    void update(IncomeDto expense) throws DAOException;
    void delete(int id) throws DAOException;
}