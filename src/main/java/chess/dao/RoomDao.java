package chess.dao;

import chess.dto.web.RoomDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

    private final ChessDataSource chessDataSource;

    public RoomDao(ChessDataSource chessDataSource) {
        this.chessDataSource = chessDataSource;
    }

    public String insert(RoomDto roomDto) {
        String query = "INSERT INTO room (name, is_opened, white, black) VALUES(?, true, ?, ?)";

        try (Connection connection = chessDataSource.connection();
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
        } catch (SQLException e) {
            throw new IllegalStateException("방을 db에 저장하는 중에 문제가 발생했습니다.", e);
        }
    }

    public List<RoomDto> openedRooms() {
        String query = "SELECT id, name, white, black FROM room WHERE is_opened = true";

        try (Connection connection = chessDataSource.connection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();) {
            List<RoomDto> result = new ArrayList<>();

            while (rs.next()) {
                result.add(new RoomDto(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4)));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException("방 리스트를 db에 불러오는 중에 문제가 발생했습니다.", e);
        }
    }

    public void close(String roomId) {
        String query = "UPDATE room SET is_opened = false WHERE id = (?)";

        try (Connection connection = chessDataSource.connection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("방 정보를 db에 갱신하는 중에 문제가 발생했습니다.", e);
        }
    }
}
