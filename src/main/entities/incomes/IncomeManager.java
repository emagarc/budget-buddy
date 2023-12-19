package main.entities.incomes;

import main.dao.dto.IncomeDto;
import main.dao.dto.UserDto;
import main.dao.interfaces.IncomeDao;
import main.dao.interfaces.IncomeManagerDao;
import main.dao.interfaces.UserDao;
import main.entities.summary.FinancialStatement;
import main.entities.summary.FinancialSummary;
import main.entities.transactions.TransactionManager;
import main.entities.user.User;
import main.exceptions.ExpenseNotFoundException;
import main.exceptions.IncomeCreationException;
import main.exceptions.IncomeNotFoundException;
import main.interfaces.transaction.TransactionCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class IncomeManager extends TransactionManager<Income> implements IncomeManagerDao {

    private final IncomeDao incomeDao;
    private final UserDao userDao;

    public IncomeManager(IncomeDao incomeDao, UserDao userDao) {
        this.incomeDao = incomeDao;
        this.userDao = userDao;
    }

    @Override
    protected Income createTransaction(double amount, TransactionCategory category, String date, User user) {
        try {
            UserDto userDto = mapUsertoUserDto(user);
            userDao.insertUser(userDto);
            int categoryId = category.getId();
            Income income = new Income(userDto.getUserId(), amount, categoryId, date);
            incomeDao.insert(income);
            return income;
        } catch (Exception e) {
            throw new IncomeCreationException("Error creating income: " + e.getMessage());
        }
    }

    @Override
    public Income removeIncome(int incomeId) {
        try {
            IncomeDto incomeToRemoveDto = incomeDao.getByIdForAdmin(incomeId);

            if (incomeToRemoveDto != null) {
                Income incomeToRemove = new Income(
                        incomeToRemoveDto.getId(),
                        incomeToRemoveDto.getAmount(),
                        incomeToRemoveDto.getCategoryId(),
                        incomeToRemoveDto.getDate()
                        // Otros parámetros si los hay
                );

                incomeDao.delete(incomeToRemoveDto);
                return incomeToRemove;
            } else {
                throw new IncomeNotFoundException("Expense not found with ID: " + incomeId);
            }
        } catch (Exception e) {
            throw new ExpenseNotFoundException("Error removing income: " + e.getMessage());
        }
    }

    @Override
    public List<Income> getUserIncomes(User user) {
        try {
            UserDto userDto = mapUsertoUserDto(user);
            List<IncomeDto> incomeDtos = incomeDao.getUserIncomes(userDto);

            // Aquí conviertes ExpenseDto a Expense
            List<Income> incomes = new ArrayList<>();
            for (IncomeDto incomeDto : incomeDtos) {
                incomes.add(convertIncomeDtoToIncome(incomeDto));
            }

            return incomes;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Income getIncomeByIdForManager(int incomeId) {
        try {
            IncomeDto incomeDto = incomeDao.getByIdForAdmin(incomeId);

            // Supongamos que ExpenseDto tiene un constructor que acepta los mismos parámetros que Expense
            return new Income(
                    incomeDto.getId(),
                    incomeDto.getAmount(),
                    incomeDto.getCategoryId(),
                    incomeDto.getDate()
                    // Otros parámetros si los hay
            );
        } catch (Exception e) {
            throw new ExpenseNotFoundException("Error getting income by ID: " + e.getMessage());
        }
    }


    @Override
    public List<Income> getAllIncomes() {
        try {
            List<IncomeDto> incomeDtoList = incomeDao.getAll();

            // Convierte la lista de ExpenseDto a Expense
            List<Income> incomeList = new ArrayList<>();
            for (IncomeDto incomeDto : incomeDtoList) {
                incomeList.add(mapIncomeDtoToIncome(incomeDto));
            }

            return incomeList;
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

    public static IncomeDto mapIncomeToIncomeDto(Income income) {
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setId(income.getId());
        incomeDto.setAmount(income.getAmount());
        incomeDto.setCategoryId(income.getCategoryId());
        incomeDto.setDate(income.getDate());
        incomeDto.setUserId(income.getUserId());
        // Otros mapeos según los atributos de Income y IncomeDto

        return incomeDto;
    }


    private Income convertIncomeDtoToIncome(IncomeDto incomeDto) {
        return new Income(
                incomeDto.getId(),
                incomeDto.getAmount(),
                incomeDto.getCategoryId(),
                incomeDto.getDate()
        );
    }

    private Income mapIncomeDtoToIncome(IncomeDto incomeDto) {
        Income income = new Income();
        income.setId(incomeDto.getId());
        income.setAmount(incomeDto.getAmount());
        income.setCategoryId(incomeDto.getCategoryId());
        income.setDate(incomeDto.getDate());
        // Otros mapeos según los atributos de ExpenseDto y Expense

        return income;
    }

}
