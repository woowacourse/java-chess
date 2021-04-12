package chess.dao;

import static chess.dao.utils.ConnectionManager.createConnection;

import chess.dao.exception.UncheckedSQLException;
import chess.dao.utils.JDBCHelper;
import chess.domain.piece.PieceType;
import chess.domain.team.Team;
import chess.entity.PieceEntity;
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
    public void insertBatch(final long gameId, final List<PieceEntity> pieceEntities) {
        final String query =
            "INSERT INTO piece(game_id, piece_type, team, x, y) VALUES (?, ?, ?, ?, ?)";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            for (final PieceEntity pieceEntity : pieceEntities) {
                pstmt.setLong(1, pieceEntity.getGameId());
                pstmt.setString(
                    2, String.valueOf(pieceEntity.getPieceType().getValue())
                );
                pstmt.setString(3, pieceEntity.getTeam().getValue());
                pstmt.setInt(4, pieceEntity.getX());
                pstmt.setInt(5, pieceEntity.getY());
                pstmt.addBatch();
                pstmt.clearParameters();
            }
            pstmt.executeBatch();
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    @Override
    public List<PieceEntity> selectAll(final long gameId) {
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

    @Override
    public Optional<PieceEntity> selectByLocation(final long gameId, final int x, final int y) {
        final String query = "SELECT * FROM piece WHERE game_id = ? AND x = ? AND y = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Arrays.asList(gameId, x, y)
            );
            final ResultSet resultSet = pstmt.executeQuery()
        ) {
            return makePiece(resultSet);
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    private Optional<PieceEntity> makePiece(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        return Optional.of(makePieceEntity(resultSet));
    }

    private PieceEntity makePieceEntity(final ResultSet resultSet) throws SQLException {
        return new PieceEntity(
            resultSet.getLong("id"),
            resultSet.getLong("game_id"),
            PieceType.from(resultSet.getString("piece_type").charAt(0)),
            Team.from(resultSet.getString("team")),
            resultSet.getInt("x"),
            resultSet.getInt("y")
        );
    }

    private List<PieceEntity> makePieces(final ResultSet resultSet) throws SQLException {
        final List<PieceEntity> pieceEntities = new ArrayList<>();
        while (resultSet.next()) {
            pieceEntities.add(makePieceEntity(resultSet));
        }
        return pieceEntities;
    }

    @Override
    public void update(final PieceEntity pieceEntity) {
        final String query = "UPDATE piece SET x = ?, y = ? WHERE id = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Arrays.asList(
                    pieceEntity.getX(), pieceEntity.getY(), pieceEntity.getId()
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
