package chess.dao;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.GameDto;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GameDao {
    private final Connection connection;

    public GameDao(final Connection connection) {
        this.connection = connection;
    }

    public void createGame(final Map<Position, Piece> board) {
        final var query = "INSERT INTO game(status,color) VALUES(?, ?)";
        try (final PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);

            ps.setBoolean(1, false);
            ps.setString(2, Color.WHITE.name());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int nweGameId = rs.getInt(1);
                createBoard(board, nweGameId);
            }

            connection.commit();
        } catch (final SQLException e) {
            rollback();
            throw new RuntimeException(e);
        } finally {
            setAutoCommitTrue();
        }
    }

    public GameDto findByLastGame() {
        final var query = "SELECT * FROM game WHERE id = (SELECT MAX(id) as id FROM game);";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            final var resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new GameDto(
                        resultSet.getInt("id"),
                        resultSet.getBoolean("status"),
                        resultSet.getString("color")
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return GameDto.EMPTY;
    }

    public void updateGameStatus(final boolean status, final int gameId) {
        final var query = "UPDATE game SET status=? WHERE id = ? ";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, status);
            ps.setInt(2, gameId);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    private void createBoard(final Map<Position, Piece> board, final int gameId) {
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

    public void movePiece(final Map<Position, Piece> board, final int gameId, final Color color) {
        final var query = "delete from board where id = ?";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            ps.setInt(1, gameId);
            ps.executeUpdate();

            createBoard(board, gameId);
            updateColor(gameId, color);

            connection.commit();
        } catch (final SQLException e) {
            rollback();
            throw new RuntimeException(e);
        } finally {
            setAutoCommitTrue();
        }
    }

    public void updateColor(final int gameId, final Color color) {
        final var query = "UPDATE game SET color=? WHERE id = ? ";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            if (color == Color.WHITE) {
                ps.setString(1, Color.BLACK.name());
            }
            if (color == Color.BLACK) {
                ps.setString(1, Color.WHITE.name());
            }
            ps.setInt(2, gameId);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setAutoCommitTrue() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
