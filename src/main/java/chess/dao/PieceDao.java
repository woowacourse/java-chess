package chess.dao;

import static chess.dao.utils.ConnectionManager.createConnection;

import chess.dao.exception.UncheckedSQLException;
import chess.entity.PieceEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
