package chess.dao;

import static chess.dao.utils.ConnectionManager.createConnection;

import chess.dao.exception.UncheckedSQLException;
import chess.dao.utils.JDBCHelper;
import chess.domain.piece.Piece;
import chess.utils.PieceConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PieceDao implements PieceDaoInterface {

    @Override
    public void insertBatch(final long gameId, final List<Piece> pieces) {
        final String query =
            "INSERT INTO piece(game_id, piece_type, team, x, y) VALUES (?, ?, ?, ?, ?)";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            for (final Piece piece : pieces) {
                pstmt.setLong(1, gameId);
                pstmt.setString(
                    2, String.valueOf(piece.getPieceType().getValue())
                );
                pstmt.setString(3, piece.getTeam().getValue());
                pstmt.setInt(4, piece.getX());
                pstmt.setInt(5, piece.getY());
                pstmt.addBatch();
                pstmt.clearParameters();
            }
            pstmt.executeBatch();
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    @Override
    public List<Piece> selectAll(final long gameId) {
        final String query = "SELECT * FROM piece WHERE game_id = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Collections.singletonList(gameId)
            );
            final ResultSet resultSet = pstmt.executeQuery()
        ) {
            return makePieces(resultSet);
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    private List<Piece> makePieces(final ResultSet resultSet) throws SQLException {
        final List<Piece> pieces = new ArrayList<>();
        while (resultSet.next()) {
            pieces.add(makePiece(resultSet));
        }
        return pieces;
    }

    private Piece makePiece(final ResultSet resultSet) throws SQLException {
        return PieceConverter.run(resultSet);
    }

    @Override
    public Optional<Piece> selectByLocation(final long gameId, final int x, final int y) {
        final String query = "SELECT * FROM piece WHERE game_id = ? AND x = ? AND y = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Arrays.asList(gameId, x, y)
            );
            final ResultSet resultSet = pstmt.executeQuery()
        ) {
            return convertSinglePiece(resultSet);
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    private Optional<Piece> convertSinglePiece(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        return Optional.of(makePiece(resultSet));
    }

    @Override
    public void updateByLocation(final long gameId, final int befX, final int befY,
        final int afterX, final int afterY) {

        final String query = "UPDATE piece SET x = ?, y = ? WHERE game_id = ? AND x = ? AND y = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Arrays.asList(
                    afterX, afterY, gameId, befX, befY
                )
            )
        ) {
            pstmt.executeUpdate();
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    @Override
    public void deleteByLocation(final long gameId, final int x, final int y) {
        final String query = "DELETE FROM piece WHERE game_id = ? AND x = ? AND y = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstm = JDBCHelper.createPreparedStatement(
                connection, query, Arrays.asList(gameId, x, y)
            )
        ) {
            pstm.executeUpdate();
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }
}
