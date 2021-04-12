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
import java.util.Collections;
import java.util.List;

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

    private List<PieceEntity> makePieces(final ResultSet resultSet) throws SQLException {
        final List<PieceEntity> pieceEntities = new ArrayList<>();
        while (resultSet.next()) {
            pieceEntities.add(
                new PieceEntity(
                    resultSet.getLong("id"),
                    resultSet.getLong("game_id"),
                    PieceType.from(resultSet.getString("piece_type").charAt(0)),
                    Team.from(resultSet.getString("team")),
                    resultSet.getInt("x"),
                    resultSet.getInt("y")
                )
            );
        }
        return pieceEntities;
    }
}
