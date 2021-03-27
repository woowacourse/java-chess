package chess.db.dao;

import static chess.db.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.db.entity.PieceEntity;
import chess.db.entity.PiecePositionEntity;
import chess.db.entity.PositionEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PiecePositionDAO {
    public PiecePositionEntity save(PiecePositionEntity piecePositionEntity) throws SQLException {
        ResultSet generatedKeys = getResultSet(piecePositionEntity);
        if (generatedKeys.next()) {
            return new PiecePositionEntity(
                generatedKeys.getLong(1),
                piecePositionEntity.getPlayerEntity(),
                piecePositionEntity.getPieceEntity(),
                piecePositionEntity.getPositionEntity());
        }
        throw new SQLException("PiecePositionEntity를 save()할 수 없습니다.");
    }

    private ResultSet getResultSet(PiecePositionEntity piecesPositionsEntity) throws SQLException {
        String query = "INSERT INTO player_piece_position (player_id, piece_id, position_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setLong(1, piecesPositionsEntity.getPlayerEntity().getId());
        pstmt.setLong(2, piecesPositionsEntity.getPieceEntity().getId());
        pstmt.setLong(3, piecesPositionsEntity.getPositionEntity().getId());
        pstmt.executeUpdate();
        return pstmt.getGeneratedKeys();
    }

    public PiecePositionEntity findByPieceAndPosition(PieceEntity pieceEntity,
        PositionEntity positionEntity) throws SQLException {
        ResultSet rs = getResultSet(pieceEntity, positionEntity);
        if (!rs.next()) {
            return null;
        }
        return new PiecePositionEntity(rs.getLong("id"));
    }

    private ResultSet getResultSet(PieceEntity pieceEntity, PositionEntity positionEntity)
        throws SQLException {
        String query = "SELECT * FROM player_piece_position WHERE piece_id = ? AND position_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, pieceEntity.getId());
        pstmt.setLong(2, positionEntity.getId());
        return pstmt.executeQuery();
    }

    public PiecePositionEntity update(PiecePositionEntity piecePositionEntity) throws SQLException {
        String query = "UPDATE player_piece_position SET piece_id = ?, position_id = ? WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, piecePositionEntity.getId());
        pstmt.setLong(2, piecePositionEntity.getPieceEntity().getId());
        pstmt.setLong(3, piecePositionEntity.getPositionEntity().getId());
        pstmt.executeUpdate();
        return piecePositionEntity;
    }


    public void delete(PiecePositionEntity piecesPositionEntity) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, piecesPositionEntity.getId());
        pstmt.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM player_piece_position";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
