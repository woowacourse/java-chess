package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import chess.exception.DataAccessException;

public class DBConnection {

    private final String URL = "jdbc:mysql://localhost:3306/chess";
    private final String USER = "user";
    private final String PASSWORD = "password";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DataAccessException("[DATABASE_ERROR] DB 연결 실패");
        }
    }
}
