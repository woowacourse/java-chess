package chess.dao.utils;

import chess.dao.exception.DatabaseDriverLoadFailureException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess_db";
    private static final String PARAMS = "?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=utf8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection createConnection() throws SQLException {
        checkDriverLoading();
        final String url = String.format("jdbc:mysql://%s/%s%s", SERVER, DATABASE, PARAMS);
        return DriverManager.getConnection(url, USER_NAME, PASSWORD);
    }

    private static void checkDriverLoading() {
        try {
            Class.forName(MYSQL_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new DatabaseDriverLoadFailureException(e.getMessage());
        }
    }
}
