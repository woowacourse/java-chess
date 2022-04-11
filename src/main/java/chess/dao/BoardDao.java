package chess.dao;

import static chess.dao.DBConnector.getConnection;

import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.view.PieceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3307/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void save(String position, String piece, String color, int gameId) {
        final String sql = "insert into board (position, piece, color, game_id) values (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position);
            statement.setString(2, piece);
            statement.setString(3, color);
            statement.setInt(4, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public void update(String from, String to, String piece, String color, int id) {
        final String delete = "delete from board where game_id = ? and position = ?";
        final String sql = "insert into board (position, piece, color, game_id) values (?, ?, ?, ?)"
                + "on duplicate key update piece = ?, color = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(delete);
             PreparedStatement statement2 = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, from);
            statement.executeUpdate();
            statement2.setString(1, to);
            statement2.setString(2, piece);
            statement2.setString(3, color);
            statement2.setInt(4, id);
            statement2.setString(5, piece);
            statement2.setString(6, color);
            statement2.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public Map<Position, Piece> findGame(int id) {
        Map<Position, Piece> board = new HashMap<>();
        final String sql = "select position, piece, color from board where game_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Position position = Position.valueOf(resultSet.getString("position"));
                Color color = Color.of(resultSet.getString("color"));
                Piece piece = PieceMapper.of(resultSet.getString("piece"), color);
                board.put(position, piece);
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return board;
    }
}
