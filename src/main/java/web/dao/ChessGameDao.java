package web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();

    public void addCommand(String command, int roomId) throws SQLException {
        try (Connection con = databaseConnection.getConnection()) {
            String query = "INSERT INTO chessGame(command, room_id) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, command);
            pstmt.setInt(2, roomId);
            pstmt.executeUpdate();
        }
    }

    public List<String> findByRoomId(int roomId) throws SQLException {
        List<String> moves = new ArrayList<>();

        try (Connection con = databaseConnection.getConnection()) {
            String query = "SELECT command FROM chessGame WHERE room_id = ? ORDER BY command_id";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, roomId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                moves.add(rs.getString("command"));
            }
        }

        return moves;
    }
}
