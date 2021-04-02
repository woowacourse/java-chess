package chess.dao;

import chess.dto.web.RoomDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDao extends ChessDao {

    public String insert(RoomDto roomDto) throws SQLException {
        String query = "INSERT INTO room (name, is_opened, white, black) VALUES(?, true, ?, ?)";

        try (Connection connection = connection();
            PreparedStatement pstmt = connection
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, roomDto.getName());
            pstmt.setString(2, roomDto.getWhite());
            pstmt.setString(3, roomDto.getBlack());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys();) {
                rs.next();
                return rs.getString(1);
            }
        }
    }

    public List<RoomDto> openedRooms() throws SQLException {
        String query = "SELECT id, name, white, black FROM room WHERE is_opened = true";

        try (Connection connection = connection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();) {
            List<RoomDto> result = new ArrayList<>();

            while (rs.next()) {
                result.add(new RoomDto(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4)));
            }
            return result;
        }
    }

    public void close(String roomId) throws SQLException {
        String query = "UPDATE room SET is_opened = false WHERE id = (?)";

        try (Connection connection = connection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        }
    }
}
