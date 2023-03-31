package chess.dao;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.piece.Piece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardJdbcDao implements BoardDao {
    private final Connection connection;

    public BoardJdbcDao(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(final Map<Position, Piece> board, final int gameId) {
        final var query = "INSERT INTO board(id,position,color,piece_type) VALUES (?,?,?,?);";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            for (Map.Entry<Position, Piece> entry : board.entrySet()) {
                Piece piece = entry.getValue();
                ps.setInt(1, gameId);
                ps.setString(2, entry.getKey().getPosition());
                ps.setString(3, piece.getColor().name());
                ps.setString(4, piece.getPieceType().name());
                ps.addBatch();
                ps.clearParameters();
            }
            ps.executeBatch();
            ps.clearParameters();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Position, Piece> findByLastGameBoard(final int gameId) {
        Map<Position, Piece> board = new HashMap<>();
        final var query = "SELECT * FROM board WHERE id = (?);";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, gameId);
            final var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Position position = Position.from(resultSet.getString("position"));
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                Color color = Color.valueOf(resultSet.getString("color"));
                board.put(position, PieceMapper.apply(pieceType, color));
            }

            return board;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll(final int gameId) {
        final var query = "delete from board where id = ?";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, gameId);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
