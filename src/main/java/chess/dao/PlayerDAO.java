package chess.dao;

import static chess.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.dao.entity.ChessRoomEntity;
import chess.dao.entity.PlayerEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {
    private final ChessRoomDAO chessRoomDAO = new ChessRoomDAO();

    public PlayerEntity add(PlayerEntity playerEntity) throws SQLException {
        String query = "INSERT INTO player (chess_room_id) VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setLong(1, playerEntity.getChessRoomEntity().getId());
        pstmt.executeUpdate();
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            playerEntity.setId(generatedKeys.getLong(1));
        }
        return playerEntity;
    }

    public PlayerEntity findById(Long id) throws SQLException {
        String query = "SELECT * FROM player WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        ChessRoomEntity chessRoomEntity = chessRoomDAO.findById(rs.getLong("chess_room_id"));
        return new PlayerEntity(rs.getLong("id"), chessRoomEntity);
    }

    public void delete(PlayerEntity playerEntity) throws SQLException {
        String query = "DELETE FROM player WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerEntity.getId());
        pstmt.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM chess_room";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
