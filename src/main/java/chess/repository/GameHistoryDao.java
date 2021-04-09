package chess.repository;

import static chess.util.Database.closeConnection;
import static chess.util.Database.getConnection;

import chess.domain.web.GameHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GameHistoryDao {
    public void addGameHistory(GameHistory gameHistory) throws SQLException {
        String query = "INSERT INTO game_history(gameId, command, createdTime) VALUES (?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, gameHistory.getGameId());
        preparedStatement.setString(2, gameHistory.getCommand());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(gameHistory.getCreatedTime()));
        preparedStatement.executeUpdate();
        closeConnection(connection);
    }

    public List<GameHistory> findAllGameHistoryByGameId(int gameId) {
        String query = "SELECT * FROM game_history gh JOIN game g ON gh.gameId = g.id WHERE gh.gameId = ?";
        List<GameHistory> gameHistories = new ArrayList<>();
        Connection connection;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, gameId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                gameHistories.add(new GameHistory(
                    rs.getInt("id"),
                    rs.getInt("gameId"),
                    rs.getString("command"),
                    rs.getTimestamp("createdTime").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        closeConnection(connection);
        return gameHistories;
    }
}
