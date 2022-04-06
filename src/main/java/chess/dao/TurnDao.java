package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TurnDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void save(final String team) {
        final Connection connection = getConnection();
        final String sql = "insert into turn (team) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, team);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        final Connection connection = getConnection();
        final String sql = "delete from turn";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
