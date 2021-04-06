package chess.repository;

import static chess.util.Database.closeConnection;
import static chess.util.Database.getConnection;

import chess.domain.web.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    public int addGame(Game game) {
        String query = "INSERT INTO game(userId, isEnd, createdTime) VALUES (?, ?, ?);";
        Connection connection = getConnection();
        int gameId;
        try {
            PreparedStatement preparedStatement = connection
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, game.getUserId());
            preparedStatement.setBoolean(2, game.isEnd());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(game.getCreatedTime()));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            gameId = rs.getInt(1);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return gameId;
    }

    public List<Game> findRunningGamesByUserId(int userId) {
        String query = "SELECT * FROM game g JOIN user u ON g.userId = u.id WHERE g.userId = ?";
        Connection connection = getConnection();
        List<Game> games = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                games.add(new Game(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getBoolean("isEnd"),
                    rs.getTimestamp("createdTime").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return games;
    }

    public void updateGameIsEnd(int gameId) throws SQLException {
        String query = "UPDATE game SET isEnd = true WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, gameId);
        preparedStatement.executeUpdate();
        closeConnection(connection);
    }
}
