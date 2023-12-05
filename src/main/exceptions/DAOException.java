package main.exceptions;

import java.sql.SQLException;

// Excepción customizada
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }
    public DAOException(String message, SQLException e) {
        super(message);
    }
}
