package main.dao.impl;

import main.dao.interfaces.UserDao;
import main.dao.dto.UserDto;
import main.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplH2 implements UserDao {
    private static final String GET_USER_BY_ID = "SELECT * FROM user_management.users WHERE id = ?";
    private static final String GET_USER_BY_USERNAME = "SELECT * FROM user_management.users WHERE username = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM user_management.users";
    private static final String INSERT_USER = "INSERT INTO user_management.users (username, email, hashed_password) VALUES (?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE user_management.users SET username = ?, email = ?, hashed_password = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM user_management.users WHERE id = ?";

    private final Connection connection;

    public UserDaoImplH2(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserDto getUserById(int userId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUserDto(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el usuario por ID", e);
        }
    }

    @Override
    public UserDto getUserByUserName(String userName) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_USERNAME)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUserDto(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el usuario por nombre de usuario", e);
        }
    }

    @Override
    public List<UserDto> getAllUsers() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            List<UserDto> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(mapResultSetToUserDto(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener la lista de usuarios", e);
        }
    }

    @Override
    public void insertUser(UserDto userDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userDto.getUserName());
            statement.setString(2, userDto.getEmail());
            statement.setString(3, userDto.getPassword());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error al insertar el usuario, ninguna fila fue afectada.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userDto.setUserId(generatedKeys.getInt(1));
                } else {
                    throw new DAOException("Error al obtener el ID generado para el usuario.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al insertar el usuario", e);
        }
    }

    @Override
    public void updateUser(UserDto userDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, userDto.getUserName());
            statement.setString(2, userDto.getEmail());
            statement.setString(3, userDto.getPassword());
            statement.setInt(4, userDto.getUserId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error al actualizar el usuario, ninguna fila fue afectada.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el usuario", e);
        }
    }

    @Override
    public void deleteUser(int userId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error al eliminar el usuario, ninguna fila fue afectada.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el usuario", e);
        }
    }

    private UserDto mapResultSetToUserDto(ResultSet resultSet) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setUserId(resultSet.getInt("id"));
        userDto.setUserName(resultSet.getString("username"));
        userDto.setEmail(resultSet.getString("email"));
        userDto.setPassword(resultSet.getString("hashed_password"));
        return userDto;
    }
}