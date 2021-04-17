package chess.dao;

import chess.controller.web.dto.MoveDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.dao.DBConnection.getConnection;

public class CommandDao {
    public void insert(Long roomId, String move_from, String move_to) {
        String query = "INSERT INTO command (room_id, move_from, move_to) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setLong(1, roomId);
            pstmt.setString(2, move_from);
            pstmt.setString(3, move_to);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("insert Error");
        }
    }

    public List<MoveDto> selectOf(Long roomId) {
        String query = "SELECT move_from, move_to FROM command WHERE room_id = (?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setLong(1, roomId);
            ResultSet rs = pstmt.executeQuery();
            return collectMoves(rs);
        } catch (SQLException e) {
            System.err.println("select All Error");
        }

        return Collections.emptyList();
    }

    private List<MoveDto> collectMoves(ResultSet rs) throws SQLException {
        List<MoveDto> moves = new ArrayList<>();
        while (rs.next()) {
            moves.add(new MoveDto(rs.getString("move_from"), rs.getString("move_to")));
        }
        return moves;
    }

    public void deleteAll() {
        String query = "DELETE FROM command";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("delete All Error");
        }
    }
}
