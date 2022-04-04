package chess.dao;

import chess.db.MySqlConnector;
import chess.dto.BoardDto;
import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    public void save(final BoardDto board) {
        final String sql = "INSERT INTO board (game_id, position, type, color) values (?, ?, ?, ?)";

        try (final Connection connection = MySqlConnector.getConnection()) {
            final Map<String, PieceDto> pieces = board.getBoard();
            for (String position : pieces.keySet()) {
                savePiece(connection, sql, board.getGameId(), position, pieces.get(position));
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePiece(final Connection connection, final String sql, final Integer gameId, final String position,
                          final PieceDto piece) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, gameId);
        statement.setString(2, position);
        statement.setString(3, piece.getType());
        statement.setString(4, piece.getColor());
        statement.execute();
    }

    public void updateOnePosition(final Integer gameId, final String position, final PieceDto piece) {
        final String sql = "UPDATE board SET type = ?, color = ? WHERE game_id =? AND position = ?";

        try (final Connection connection = MySqlConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, piece.getType());
            statement.setString(2, piece.getColor());
            statement.setInt(3, gameId);
            statement.setString(4, position);
            statement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public BoardDto findByGameId(final Integer gameId) {
        final String sql = "SELECT * FROM board WHERE game_id = ?";

        try (final Connection connection = MySqlConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);

            final ResultSet resultSet = statement.executeQuery();
            final Map<String, PieceDto> board = new HashMap<>();
            while (resultSet.next()) {
                board.put(resultSet.getString("position"),
                        new PieceDto(resultSet.getString("type"), resultSet.getString("color")));
            }

            return new BoardDto(gameId, board);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
