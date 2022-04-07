package chess.web.dao;

import chess.domain.Color;
import chess.web.dto.GameStateDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDao {

    private static final String URL = "jdbc:mysql://localhost:3310/chess";
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

    public void save(GameStateDto gameStateDto) {
        final Connection connection = getConnection();
        final String sql = "insert into board (id, turn) values (?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
            statement.setString(2, gameStateDto.getTurn());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Color find() {
        final Connection connection = getConnection();
        final String sql = "select * from board where id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 1);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Color.findByValue(resultSet.getString("turn"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Color.findByValue("");
    }

    public void update(GameStateDto gameStateDto) {
        final Connection connection = getConnection();
        final String sql = "update board set turn = ? where id = 1";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameStateDto.getTurn());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
