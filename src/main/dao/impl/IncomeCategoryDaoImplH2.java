package main.dao.impl;

import main.dao.IncomeCategoryDao;
import main.entities.incomes.IncomeCategory;
import main.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeCategoryDaoImplH2 implements IncomeCategoryDao {
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM incomes_management.categories WHERE id = ?";
    private final Connection connection;

    public IncomeCategoryDaoImplH2(Connection connection) {
        this.connection = connection;
    }

    @Override
    public IncomeCategory getCategoryById(int categoryId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_ID)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToIncomeCategory(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener la categoría por ID", e);
        }
    }

    private IncomeCategory mapResultSetToIncomeCategory(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        return new IncomeCategory(name);
    }
}
