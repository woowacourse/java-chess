package chess.dao;

import chess.controller.web.dto.RoomDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.DBConnection.getConnection;

public class RoomDao {
    public long insert(String roomName) {
        String query = "INSERT INTO room (room_name) VALUES (?)";
        String query2 = "SELECT last_insert_id();";
        long last_inserted_id = 0L;

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             PreparedStatement pstmt2 = connection.prepareStatement(query2)) {
            pstmt.setString(1, roomName);
            pstmt.executeUpdate();
            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
                last_inserted_id = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return last_inserted_id;
    }

    public List<RoomDto> selectAll() {
        String query = "SELECT * FROM room";
        List<RoomDto> roomDtos = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                roomDtos.add(new RoomDto(rs.getLong("room_id"), rs.getString("room_name")));
            }
        } catch (SQLException e) {
            System.err.println("select All Error");
        }
        return roomDtos;
    }


    public void delete(Long roomId) {
        String query = "DELETE FROM room WHERE room_id = (?)";
        try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, roomId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("delete Error");
        }
    }

    public void deleteAll() {
        String query = "DELETE FROM room";
        try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("select All Error");
        }
    }
}
