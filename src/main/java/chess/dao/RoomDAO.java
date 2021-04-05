package chess.dao;

import chess.dto.response.ResponseCode;
import chess.exception.ChessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class RoomDAO {
    private static final int FIRST_PARAMETER_INDEX = 1;
    private static final int FIRST_COLUMN = 1;
    private static final String ROOM_ID_COLUMN_NAME = "roomId";

    private final ConnectionSetup con;

    public RoomDAO() {
        con = new ConnectionSetup();
    }

    public long createRoom(String roomName) throws SQLException {
        try (Connection connection = con.getConnection()) {
            String query = "INSERT INTO room (roomName) VALUES (?)";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(FIRST_PARAMETER_INDEX, roomName);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                long roomId = rs.getLong(FIRST_COLUMN);
                return roomId;
            }
            throw new ChessException(ResponseCode.WRONG_ARGUMENTS_INSERT_ERROR);
        }
    }

    public Optional<Long> findRoomIdByName(String roomName) throws SQLException {
        try (Connection connection = con.getConnection()) {
            String query = "SELECT roomId FROM room WHERE roomName = ? ORDER BY roomId DESC";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(FIRST_PARAMETER_INDEX, roomName);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.ofNullable(rs.getLong(ROOM_ID_COLUMN_NAME));
        }
    }
}
