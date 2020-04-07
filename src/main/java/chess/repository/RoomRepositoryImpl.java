package chess.repository;

import chess.dto.RoomDto;
import chess.result.Result;
import chess.utils.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static chess.utils.dbConnector.getConnection;

public class RoomRepositoryImpl implements RoomRepository {
    @Override
    public Result create(RoomDto roomDto) throws SQLException {
        String query = "INSERT INTO room VALUES (?, ?, ?, ?, ?)";
        int roomId = IdGenerator.generateRoomId();
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomId);
        pstmt.setInt(2, roomDto.getBlackUserId());
        pstmt.setInt(3, roomDto.getWhiteUserId());
        pstmt.setBoolean(4, roomDto.isEnd());
        pstmt.setString(5, roomDto.getName());
        pstmt.executeUpdate();
        return new Result(true, roomId);
    }

    @Override
    public Result findById(int roomId) throws SQLException {
        String query = "SELECT * FROM room WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return new Result(false, "Can not find room.");
        }

        return new Result(true,
                new RoomDto(rs.getInt("id"),
                        rs.getInt("black_user_id"),
                        rs.getInt("white_user_id"),
                        rs.getBoolean("is_end"),
                        rs.getString("name")));

    }

    @Override
    public Result findByName(String roomName) throws SQLException {
        String query = "SELECT * FROM room WHERE name = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, roomName);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return new Result(false, "Can not find room.");
        }

        return new Result(true,
                new RoomDto(rs.getInt("id"),
                        rs.getInt("black_user_id"),
                        rs.getInt("white_user_id"),
                        rs.getBoolean("is_end"),
                        rs.getString("name")));
    }

    @Override
    public Result update(RoomDto roomDto) throws SQLException {
        String query = "UPDATE room SET black_user_id = ?, white_user_id = ?, is_end = ? WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomDto.getBlackUserId());
        pstmt.setInt(2, roomDto.getWhiteUserId());
        pstmt.setBoolean(3, roomDto.isEnd());
        pstmt.setInt(4, roomDto.getRoomId());
        pstmt.executeUpdate();
        return new Result(true, null);
    }

    @Override
    public Result delete(int roomId) throws SQLException {
        String query = "DELETE FROM room WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomId);
        pstmt.executeUpdate();
        return new Result(true, null);
    }

    @Override
    public Result deleteAll() throws SQLException {
        String query = "DELETE FROM room";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
        return new Result(true, null);
    }
}
