package main.dao.impl;

import main.dao.ExpenseDao;
import main.dao.ExpenseManagerDao;
import main.dao.UserDao;
import main.dao.dto.ExpenseDto;
import main.entities.expenses.Expense;
import main.entities.user.User;
import main.exceptions.DAOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseManagerDaoImplH2 implements ExpenseManagerDao {
    private final ExpenseDao expenseDao;
    private final UserDao userDao;

    public ExpenseManagerDaoImplH2(ExpenseDao expenseDao, UserDao userDao) {
        this.expenseDao = expenseDao;
        this.userDao = userDao;
    }

    @Override
    public void createExpense(Expense expense) {
        try {
            expenseDao.insert(expense);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        try {
            ExpenseDto expenseDto = expenseDao.getById(expenseId, 0); //
            return convertDtoToExpense(expenseDto);
        } catch (DAOException e) {
            e.printStackTrace(); //
        }
    }


    @Override
    public Expense removeExpense(int expenseId) {
        try {
            ExpenseDto expenseDto = expenseDao.getById(expenseId, 0);
            Expense removedExpense = convertDtoToExpense(expenseDto);

            expenseDao.delete(expenseId);
            return removedExpense;
        } catch (DAOException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
            return null;
        }
    }

    @Override
    public List<Expense> getUserExpenses(User user) {
        try {
            return expenseDao.getUserExpenses(user);
        } catch (DAOException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
            return Collections.emptyList();
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        try {
            List<ExpenseDto> expenseDtoList = expenseDao.getAll();
            List<Expense> expenseList = new ArrayList<>();

            for (ExpenseDto expenseDto : expenseDtoList) {
                Expense expense = convertDtoToExpense(expenseDto);
                expenseList.add(expense);
            }

            return expenseList;
        } catch (DAOException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
            return Collections.emptyList();
        }
    }

    private Expense convertDtoToExpense(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setId(expenseDto.getId());
        expense.setUserId(expenseDto.getUserId());
        expense.setAmount(expenseDto.getAmount());
        expense.setCategoryId(expenseDto.getCategoryId());
        expense.setDate(expenseDto.getDate());
        // Asegúrate de establecer otros atributos según sea necesario
        return expense;
    }
}
