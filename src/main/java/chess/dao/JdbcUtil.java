package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;

public class JdbcUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
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

    public static void setStringsToStatement(PreparedStatement statement, Map<Integer, String> params) throws SQLException {
        for (Entry<Integer, String> param : params.entrySet()) {
            statement.setString(param.getKey(), param.getValue());
        }
    }
}

