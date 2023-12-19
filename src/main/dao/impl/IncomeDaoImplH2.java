package main.dao.impl;

import main.dao.interfaces.IncomeDao;
import main.dao.dto.IncomeDto;
import main.dao.dto.UserDto;
import main.entities.incomes.Income;
import main.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeDaoImplH2 implements IncomeDao {
    private static final String GET_INCOME_BY_ID = "SELECT * FROM incomes_management.incomes WHERE id = ?";
    private static final String GET_ALL_USER_INCOMES = "SELECT * FROM incomes_management.incomes WHERE user_id = ?";
    private static final String INSERT_INTO_INCOME = "INSERT INTO incomes_management.incomes (amount, categoryId, date, user_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_INCOME = "UPDATE incomes_management.incomes SET amount = ?, categoryId = ?, date = ? WHERE id = ?";
    private static final String DELETE_INCOME = "DELETE FROM incomes_management.incomes WHERE id = ?";

    private final Connection connection;

    public IncomeDaoImplH2(Connection connection) {
        this.connection = connection;
    }

    @Override
    public IncomeDto getById(int id, UserDto requestingUser) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_INCOME_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                IncomeDto incomeDto = mapResultSetToIncomeDto(resultSet);

                // Verifica si el userId asociado al ingreso coincide con el userId de la solicitud
                if (incomeDto.getUserId() == requestingUser.getUserId()) {
                    return incomeDto;
                } else {
                    // Puedes lanzar una excepción indicando que el ingreso no pertenece al usuario solicitante
                    throw new DAOException("El ingreso solicitado no pertenece al usuario solicitante");
                }
            }
            return null; // Si no se encuentra el ingreso, puedes devolver null
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el ingreso por ID", e);
        }
    }

    @Override
    public IncomeDto getByIdForAdmin(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_INCOME_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToIncomeDto(resultSet);
            }
            return null; // Si no se encuentra el ingreso, puedes devolver null
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el ingreso por ID para administrador", e);
        }
    }

    @Override
    public List<IncomeDto> getUserIncomes(UserDto userDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USER_INCOMES)) {
            statement.setInt(1, userDto.getUserId());
            ResultSet resultSet = statement.executeQuery();
            List<IncomeDto> incomes = new ArrayList<>();
            while (resultSet.next()) {
                incomes.add(mapResultSetToIncomeDto(resultSet));
            }
            return incomes;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener los ingresos del usuario", e);
        }
    }

    @Override
    public List<IncomeDto> getAll() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USER_INCOMES)) {
            ResultSet resultSet = statement.executeQuery();
            List<IncomeDto> incomes = new ArrayList<>();
            while (resultSet.next()) {
                incomes.add(mapResultSetToIncomeDto(resultSet));
            }
            return incomes;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener la lista de ingresos", e);
        }
    }

    @Override
    public void insert(IncomeDto incomeDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_INCOME, Statement.RETURN_GENERATED_KEYS)) {
            // Utiliza mapDtoToExpense para obtener el objeto Income
            Income income = mapDtoToIncome(incomeDto);

            statement.setDouble(1, income.getAmount());
            statement.setInt(2, income.getCategoryId());
            statement.setString(3, income.getDate());
            statement.setInt(4, income.getUserId()); // Set the user_id

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error al insertar el ingreso, ninguna fila fue afectada.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al insertar el ingreso", e);
        }
    }

    @Override
    public void update(IncomeDto incomeDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_INCOME)) {

            // Solo necesitas los atributos que pueden ser actualizados
            statement.setDouble(1, incomeDto.getAmount());
            statement.setInt(2, incomeDto.getCategoryId());
            statement.setString(3, incomeDto.getDate());

            // Asumo que estás usando el ID del IncomeDto para la actualización
            statement.setInt(4, incomeDto.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error al actualizar el ingreso, ninguna fila fue afectada.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el ingreso", e);
        }
    }

    @Override
    public void delete(IncomeDto incomeDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_INCOME)) {
            statement.setInt(1, incomeDto.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error al eliminar el ingreso, ninguna fila fue afectada.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el ingreso", e);
        }
    }

    private Income mapDtoToIncome(IncomeDto incomeDto) {
        Income income = new Income();
        income.setAmount(incomeDto.getAmount());
        income.setCategoryId(incomeDto.getCategoryId());
        income.setDate(incomeDto.getDate());
        income.setUserId(incomeDto.getUserId()); // Set the userId
        return income;
    }

    private IncomeDto mapResultSetToIncomeDto(ResultSet resultSet) throws SQLException {
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setAmount(resultSet.getDouble("amount")); // Use getDouble for the correct data type
        incomeDto.setDate(resultSet.getString("date"));
        incomeDto.setCategoryId(resultSet.getInt("category_id"));
        incomeDto.setUserId(resultSet.getInt("user_id")); // Set the userId
        return incomeDto;
    }
}