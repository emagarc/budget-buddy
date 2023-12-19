package main.dao.impl;

import main.dao.interfaces.ExpenseDao;
import main.dao.interfaces.ExpenseManagerDao;
import main.dao.interfaces.UserDao;
import main.dao.dto.ExpenseDto;
import main.dao.dto.UserDto;
import main.entities.expenses.Expense;
import main.entities.summary.FinancialStatement;
import main.entities.summary.FinancialSummary;
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
    public Expense getExpenseByIdForManager(int expenseId) {
        try {
            ExpenseDto expenseDto = expenseDao.getByIdForAdmin(expenseId);
            return convertDtoToExpense(expenseDto);
        } catch (DAOException e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense removeExpense(int expenseId) {
        try {
            ExpenseDto expenseDto = expenseDao.getByIdForAdmin(expenseId);
            Expense removedExpense = convertDtoToExpense(expenseDto);

            expenseDao.delete(expenseId);
            return removedExpense;
        } catch (DAOException e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getUserExpenses(User user) {
        try {
            List<ExpenseDto> expenseDtos = expenseDao.getUserExpenses(mapUsertoUserDto(user));

            // Aquí conviertes ExpenseDto a Expense
            List<Expense> expenses = new ArrayList<>();
            for (ExpenseDto expenseDto : expenseDtos) {
                expenses.add(convertDtoToExpense(expenseDto));
            }

            return expenses;
        } catch (DAOException e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
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
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public FinancialSummary getFinancialSummary(User user) {
        // Implementar lógica para obtener un resumen financiero
        return new FinancialSummary(user);
    }

    @Override
    public FinancialStatement getFinancialStatement(User user) {
        // Implementar lógica para obtener un estado financiero
        return new FinancialStatement(user);
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

    private UserDto mapUsertoUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
