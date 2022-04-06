package chess.dao;

import chess.dao.util.DatabaseConnector;
import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    public static int gameId = 0;

    DatabaseConnector databaseConnector = new DatabaseConnector();

    public int create() {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "insert into game (id, turn) values (?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ++gameId);
            statement.setString(2, Color.BLACK.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(connection);

        return gameId;
    }

    public Color findTurnById(int gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select id, turn from game where id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("유효하지 않은 게임입니다.");
            }
            return Color.of(resultSet.getString("turn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(statement, resultSet, connection);
        return null;
    }

    public void updateTurnById(int gameId, Color nextTurn) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "update game set turn = ? where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nextTurn.getName());
            statement.setInt(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(connection);
    }
}
