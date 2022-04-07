package chess.dao;

import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChessGameDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Long save(final Color turn) {
        final Connection connection = getConnection();
        final String sql = "insert into chessGame (turn) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, turn.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                return null;
            }
            long gameId = generatedKeys.getLong(1);
            connection.close();
            return gameId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public Color findTurnById(final Long id) {
        final Connection connection = getConnection();
        final String sql = "select turn from chessGame where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Color turn = Color.from(resultSet.getString("turn"));
            connection.close();
            return turn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(final Long id) {
        final Connection connection = getConnection();
        final String sql = "delete from chessGame where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTurnById(final Long id, final String turn) {
        final Connection connection = getConnection();
        final String sql = "update chessGame set turn = ? where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, turn);
            statement.setLong(2, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
