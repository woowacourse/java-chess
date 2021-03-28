package chess.db.dao;

import static chess.db.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.db.domain.board.PiecePositionFromDB;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PiecePositionEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiecePositionDAO {
    public PiecePositionEntity save(PiecePositionEntity playerPiecePositionEntity)
        throws SQLException {
        ResultSet generatedKeys = getResultSet(playerPiecePositionEntity);
        if (generatedKeys.next()) {
            return new PiecePositionEntity(
                generatedKeys.getLong(1),
                playerPiecePositionEntity.getPlayerEntity(),
                playerPiecePositionEntity.getPieceEntity(),
                playerPiecePositionEntity.getPositionEntity());
        }
        throw new SQLException("PiecePositionEntity를 save()할 수 없습니다.");
    }

    private ResultSet getResultSet(PiecePositionEntity playerPiecePositionEntity)
        throws SQLException {
        String query = "INSERT INTO player_piece_position (player_id, piece_id, position_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setLong(1, playerPiecePositionEntity.getPlayerEntity().getId());
        pstmt.setLong(2, playerPiecePositionEntity.getPieceEntity().getId());
        pstmt.setLong(3, playerPiecePositionEntity.getPositionEntity().getId());
        pstmt.executeUpdate();
        return pstmt.getGeneratedKeys();
    }

    public PiecePositionEntity findByPlayerAndPieceAndPosition(PlayerEntity playerEntity,
        PieceEntity pieceEntity, PositionEntity positionEntity) throws SQLException {
        ResultSet rs = getResultSet(playerEntity, pieceEntity, positionEntity);
        if (!rs.next()) {
            return null;
        }
        return new PiecePositionEntity(
            rs.getLong("id"),
            playerEntity,
            pieceEntity,
            positionEntity);
    }

    private ResultSet getResultSet(PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity)
        throws SQLException {
        String query = "SELECT * FROM player_piece_position WHERE player_id = ? AND piece_id = ? AND position_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerEntity.getId());
        pstmt.setLong(2, pieceEntity.getId());
        pstmt.setLong(3, positionEntity.getId());
        return pstmt.executeQuery();
    }

    public List<PiecePositionFromDB> findAllByPlayer(PlayerEntity playerEntity)
        throws SQLException {
        ResultSet rs = getResultSet(playerEntity);
        List<PiecePositionFromDB> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new PiecePositionFromDB(
                rs.getString("piece_name"),
                rs.getString("piece_color"),
                rs.getString("file_value"),
                rs.getString("rank_value")));
        }
        return results;
    }

    private ResultSet getResultSet(PlayerEntity playerEntity) throws SQLException {
        String query =
            "SELECT piece.name AS piece_name, piece.color AS piece_color, position.file_value AS file_value, position.rank_value AS rank_value "
                + "FROM player_piece_position "
                + "INNER JOIN piece ON player_piece_position.piece_id = piece.id "
                + "INNER JOIN position ON player_piece_position.position_id = position.id "
                + "WHERE player_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerEntity.getId());
        return pstmt.executeQuery();
    }

    public PiecePositionEntity updatePieceAndPosition(
        PiecePositionEntity playerPiecePositionEntity)
        throws SQLException {
        String query = "UPDATE player_piece_position SET piece_id = ?, position_id = ? WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerPiecePositionEntity.getPieceEntity().getId());
        pstmt.setLong(2, playerPiecePositionEntity.getPositionEntity().getId());
        pstmt.setLong(3, playerPiecePositionEntity.getId());
        pstmt.executeUpdate();
        return playerPiecePositionEntity;
    }


    public void remove(PiecePositionEntity piecesPositionEntity) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, piecesPositionEntity.getId());
        pstmt.executeUpdate();
    }

    public void removeAll() throws SQLException {
        String query = "DELETE FROM player_piece_position";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

    public void removeAllByPlayer(PlayerEntity playerEntity) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE player_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerEntity.getId());
        pstmt.executeUpdate();
    }
}
