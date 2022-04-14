package chess.dao;

import static chess.dao.DBConnector.getConnection;

import chess.domain.game.state.ChessGame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChessGameDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public int findByRoomId(String roomId) {
        final String sql = "select id from game where room_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException exception) {
            logger.warn(exception.getMessage());
        }
        return 0;
    }

    public void save(ChessGame chessGame, String roomId) {
        final String sql = "insert into game (state, room_id) values (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chessGame.getClass().getSimpleName().toLowerCase());
            statement.setString(2, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public void update(int id, ChessGame chessGame) {
        final String sql = "update game set state = ? where id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chessGame.getClass().getSimpleName().toLowerCase());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public String findById(int id) {
        final String sql = "select state from game where id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("state");
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }
}
