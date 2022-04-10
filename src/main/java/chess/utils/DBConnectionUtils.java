package chess.utils;

import chess.view.OutputView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnectionUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "zero";
    private static final String PASSWORD = "1234";
    private static final String OPTION = "?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";

    private DBConnectionUtils() {
    }

    public static Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL + OPTION, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(final Connection connection) {
        try {
            closeConnect(connection);
        } catch (SQLException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void closeConnect(final Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
