package chess.dao;

import static chess.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.dao.entity.ChessRoomEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessRoomDAO {

    public ChessRoomEntity add(ChessRoomEntity chessRoomEntity) throws SQLException {
        String query = "INSERT INTO chess_room (title, current_turn_team_color) VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setString(1, chessRoomEntity.getTitle());
        pstmt.setString(2, chessRoomEntity.getCurrentTeamColor());
        pstmt.executeUpdate();
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            chessRoomEntity.setId(generatedKeys.getLong(1));
        }
        return chessRoomEntity;
    }

    public ChessRoomEntity findById(Long id) throws SQLException {
        String query = "SELECT * FROM chess_room WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new ChessRoomEntity(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("current_turn_team_color"));
    }

    public void delete(ChessRoomEntity chessRoomEntity) throws SQLException {
        String query = "DELETE FROM chess_room WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, chessRoomEntity.getId());
        pstmt.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM chess_room";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
