package main.entities.expenses;

import main.dao.ExpenseDao;
import main.dao.UserDao;
import main.dao.dto.ExpenseDto;
import main.dao.dto.UserDto;
import main.entities.summary.FinancialStatement;
import main.entities.summary.FinancialSummary;
import main.entities.transactions.TransactionManager;
import main.entities.user.User;
import main.exceptions.ExpenseCreationException;
import main.exceptions.ExpenseNotFoundException;
import main.interfaces.transaction.TransactionCategory;

import java.util.Collections;
import java.util.List;

import main.dao.ExpenseManagerDao;


public class ExpenseManager extends TransactionManager<Expense> implements ExpenseManagerDao {

    private final ExpenseDao expenseDao;
    private final UserDao userDao;

    public ExpenseManager(ExpenseDao expenseDao, UserDao userDao) {
        this.expenseDao = expenseDao;
        this.userDao = userDao;
    }

    @Override
    protected Expense createTransaction(double amount, TransactionCategory category, String date, User user) {
        try {
            UserDto userDto = mapUsertoUserDto(user);
            userDao.insertUser(userDto);
            int categoryId = category.getId();
            Expense expense = new Expense(userDto.getUserId(), amount, categoryId, date);
            expenseDao.insert(expense);
            return expense;
        } catch (Exception e) {
            throw new ExpenseCreationException("Error creating expense: " + e.getMessage());
        }
    }

    @Override
    public Expense removeExpense(int expenseId) {
        try {
            ExpenseDto expenseToRemoveDto = expenseDao.getByIdForAdmin(expenseId);

            if (expenseToRemoveDto != null) {
                Expense expenseToRemove = new Expense(
                        expenseToRemoveDto.getId(),
                        expenseToRemoveDto.getAmount(),
                        expenseToRemoveDto.getCategoryId(),
                        expenseToRemoveDto.getDate()
                        // Otros parámetros si los hay
                );

                expenseDao.delete(expenseToRemoveDto);
                return expenseToRemove;
            } else {
                throw new ExpenseNotFoundException("Expense not found with ID: " + expenseId);
            }
        } catch (Exception e) {
            throw new ExpenseNotFoundException("Error removing expense: " + e.getMessage());
        }
    }

    @Override
    public Expense getExpenseByIdForManager(int expenseId) {
        try {
            ExpenseDto expenseDto = expenseDao.getByIdForAdmin(expenseId);

            // Supongamos que ExpenseDto tiene un constructor que acepta los mismos parámetros que Expense
            return new Expense(
                    expenseDto.getId(),
                    expenseDto.getAmount(),
                    expenseDto.getCategoryId(),
                    expenseDto.getDate()
                    // Otros parámetros si los hay
            );
        } catch (Exception e) {
            throw new ExpenseNotFoundException("Error getting expense by ID: " + e.getMessage());
        }
    }


    @Override
    public List<Expense> getAllExpenses() {
        try {
            return expenseDao.getAll();  // Utiliza el método getAll de ExpenseDao
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Expense> getAllExpenses () {
        try {
            return expenseManagerDao.getAllExpenses();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public FinancialSummary getFinancialSummary(User user) {
        return new FinancialSummary(user);
    }

    public FinancialStatement getFinancialStatement(User user) {
        return new FinancialStatement(user);
    }

    private UserDto mapUsertoUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }


}
