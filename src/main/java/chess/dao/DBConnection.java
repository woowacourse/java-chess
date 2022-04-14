package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import chess.exception.DataAccessException;

public class DBConnection {

    private final String url;
    private final String user;
    private final String password;

    public DBConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DataAccessException("[DATABASE_ERROR] DB 연결 실패");
        }
    }
}
