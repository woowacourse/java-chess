package chess.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnector {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadConnection();
        return createConnection();
    }

    public void loadConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void closeConnection(final Connection connection, final PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(final Connection connection, final PreparedStatement preparedStatement, final ResultSet resultSet) {
        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
