package chess.dao;

import chess.exception.DataAccessException;
import chess.exception.DriverLoadException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectDB {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
    private static final String USER_NAME = "root";
    private static final String USER_PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USER_NAME, USER_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new DriverLoadException();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
