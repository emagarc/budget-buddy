package main.dao.interfaces;

import main.dao.dto.UserDto;
import main.entities.user.User;
import main.exceptions.DAOException;

import java.util.List;

public interface UserDao {
    UserDto getUserById(int userId) throws DAOException;

    UserDto getUserByUserName(String userName) throws DAOException;

    List<UserDto> getAllUsers() throws DAOException;

    void insertUser(UserDto userDto) throws DAOException;

    void updateUser(UserDto userDto) throws DAOException;

    void deleteUser(int userId) throws DAOException;
}