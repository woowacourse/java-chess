package chess.dao;

import static chess.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.dao.entity.PieceEntity;
import chess.dao.entity.PositionEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDAO {
    private final PieceDAO pieceDAO = new PieceDAO();

    public PositionEntity add(PositionEntity positionEntity) throws SQLException {
        String query = "INSERT INTO position (file_value, rank_value, piece_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setString(1, positionEntity.getFileValue());
        pstmt.setString(2, positionEntity.getRankValue());
        pstmt.setLong(3, positionEntity.getPieceEntity().getId());
        pstmt.executeUpdate();
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            positionEntity.setId(generatedKeys.getLong(1));
        }
        return positionEntity;
    }

    public PositionEntity findById(Long id) throws SQLException {
        String query = "SELECT * FROM position WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        PieceEntity pieceEntity = pieceDAO.findById(rs.getLong("piece_id"));
        return new PositionEntity(
            rs.getLong("id"),
            rs.getString("file_value"),
            rs.getString("rank_value"),
            pieceEntity);
    }

    public PositionEntity update(PositionEntity positionEntity) throws SQLException {
        String query = "UPDATE position SET file_value = ?, rank_value = ? WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, positionEntity.getId());
        pstmt.setString(2, positionEntity.getFileValue());
        pstmt.setString(3, positionEntity.getRankValue());
        pstmt.executeUpdate();
        return positionEntity;
    }

    public void delete(PositionEntity positionEntity) throws SQLException {
        String query = "DELETE FROM position WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, positionEntity.getId());
        pstmt.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM position";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
