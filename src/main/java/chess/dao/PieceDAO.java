package chess.dao;

import static chess.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.dao.entity.PieceEntity;
import chess.dao.entity.PlayerEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDAO {
    private final PlayerDAO playerDAO = new PlayerDAO();

    public PieceEntity add(PieceEntity pieceEntity) throws SQLException {
        String query = "INSERT INTO piece (piece_name, color, player_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setString(1, pieceEntity.getPieceName());
        pstmt.setString(2, pieceEntity.getColor());
        pstmt.setLong(3, pieceEntity.getPlayerEntity().getId());
        pstmt.executeUpdate();
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            pieceEntity.setId(generatedKeys.getLong(1));
        }
        return pieceEntity;
    }

    public PieceEntity findById(Long id) throws SQLException {
        String query = "SELECT * FROM piece WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        PlayerEntity playerEntity = playerDAO.findById(rs.getLong("player_id"));
        return new PieceEntity(
            rs.getLong("id"),
            rs.getString("piece_name"),
            rs.getString("color"),
            playerEntity);
    }

    public void delete(PieceEntity pieceEntity) throws SQLException {
        String query = "DELETE FROM piece WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, pieceEntity.getId());
        pstmt.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM piece";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
