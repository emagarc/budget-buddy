package main.dao.impl;

import main.dao.interfaces.ExpenseCategoryDao;
import main.entities.expenses.ExpenseCategory;
import main.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseCategoryDaoImplH2 implements ExpenseCategoryDao {
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM expenses_management.categories WHERE id = ?";
    private final Connection connection;

    public ExpenseCategoryDaoImplH2(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ExpenseCategory getCategoryById(int categoryId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_ID)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToExpenseCategory(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener la categoría por ID", e);
        }
    }

    private ExpenseCategory mapResultSetToExpenseCategory(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        return new ExpenseCategory(name);
    }
}
