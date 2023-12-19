package main.dao.impl;

import main.dao.interfaces.ExpenseDao;
import main.dao.dto.ExpenseDto;
import main.dao.dto.UserDto;
import main.entities.expenses.Expense;
import main.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImplH2 implements ExpenseDao {
    private static final String GET_EXPENSE_BY_ID = "SELECT * FROM expenses_management.expenses WHERE id = ?";
    private static final String GET_ALL_USER_EXPENSES = "SELECT * FROM expenses_management.expenses WHERE user_id = ?";
    private static final String INSERT_INTO_EXPENSE = "INSERT INTO expenses_management.expenses (amount, categoryId, date, user_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_EXPENSE = "UPDATE expenses_management.expenses SET amount = ?, categoryId = ?, date = ? WHERE id = ?";
    private static final String DELETE_EXPENSE = "DELETE FROM expenses_management.expenses WHERE id = ?";

    private final Connection connection;

    public ExpenseDaoImplH2(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ExpenseDto getById(int id, UserDto requestingUser) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_EXPENSE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ExpenseDto expenseDto = mapResultSetToExpenseDto(resultSet);

                // Verifica si el userId asociado al gasto coincide con el userId de la solicitud
                if (expenseDto.getUserId() == requestingUser.getUserId()) {
                    return expenseDto;
                } else {
                    // Puedes lanzar una excepción indicando que el gasto no pertenece al usuario solicitante
                    throw new DAOException("El gasto solicitado no pertenece al usuario solicitante");
                }
            }
            return null; // Si no se encuentra el gasto, puedes devolver null
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el gasto por ID", e);
        }
    }

    @Override
    public ExpenseDto getByIdForAdmin(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_EXPENSE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ExpenseDto expenseDto = mapResultSetToExpenseDto(resultSet);
                return expenseDto;
            }
            return null; // Si no se encuentra el gasto, puedes devolver null
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el gasto por ID para el administrador", e);
        }
    }

    @Override
    public List<ExpenseDto> getUserExpenses(UserDto userDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USER_EXPENSES)) {
            statement.setInt(1, userDto.getUserId());
            ResultSet resultSet = statement.executeQuery();
            List<ExpenseDto> expenses = new ArrayList<>();
            while (resultSet.next()) {
                expenses.add(mapResultSetToExpenseDto(resultSet));
            }
            return expenses;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener la lista de gastos para el usuario", e);
        }
    }

    @Override
    public List<ExpenseDto> getAll() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USER_EXPENSES)) {
            ResultSet resultSet = statement.executeQuery();
            List<ExpenseDto> expenses = new ArrayList<>();
            while (resultSet.next()) {
                expenses.add(mapResultSetToExpenseDto(resultSet));
            }
            return expenses;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener la lista de gastos", e);
        }
    }

    @Override
    public void insert(Expense expenseToInsert) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_EXPENSE, Statement.RETURN_GENERATED_KEYS)) {
            // Utiliza mapDtoToExpense para obtener el objeto Expense
            ExpenseDto expenseDto = mapExpenseToDto(expenseToInsert);

            statement.setDouble(1, expenseToInsert.getAmount());
            statement.setInt(2, expenseToInsert.getCategoryId());
            statement.setString(3, expenseToInsert.getDate());
            statement.setInt(4, expenseToInsert.getUserId()); // Set the user_id

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error al insertar el gasto, ninguna fila fue afectada.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al insertar el gasto", e);
        }
    }

    @Override
    public void update(ExpenseDto expenseDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_EXPENSE)) {

            // Solo necesitas los atributos que pueden ser actualizados
            statement.setDouble(1, expenseDto.getAmount());
            statement.setInt(2, expenseDto.getCategoryId());
            statement.setString(3, expenseDto.getDate());

            // Asumo que estás usando el ID del ExpenseDto para la actualización
            statement.setInt(4, expenseDto.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error al actualizar el gasto, ninguna fila fue afectada.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el gasto", e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_EXPENSE)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error al eliminar el gasto, ninguna fila fue afectada.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el gasto", e);
        }
    }

    private ExpenseDto mapExpenseToDto(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setAmount(expense.getAmount());
        expenseDto.setCategoryId(expense.getCategoryId());
        expenseDto.setDate(expense.getDate());
        expenseDto.setUserId(expense.getUserId()); // Set the userId
        return expenseDto;
    }


    private ExpenseDto mapResultSetToExpenseDto(ResultSet resultSet) throws SQLException {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setAmount(resultSet.getDouble("amount")); // Use getDouble for the correct data type
        expenseDto.setDate(resultSet.getString("date"));
        expenseDto.setCategoryId(resultSet.getInt("category_id"));
        expenseDto.setUserId(resultSet.getInt("user_id")); // Set the userId
        return expenseDto;
    }
}