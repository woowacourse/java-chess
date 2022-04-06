package chess.dao;

import chess.view.Square;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save(final String command) {
        final Connection connection = getConnection();
        final String sql = "insert into command (command) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, command);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> findAll() {
        final Connection connection = getConnection();
        final String sql = "select command from command";
        final List<String> commands = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commands.add(resultSet.getString("command"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commands;
    }

    public void init() {
        final Connection connection = getConnection();
        final String sql = "delete from command";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
