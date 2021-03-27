package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomDAO {
    private static final int FIRST_PARAMETER_INDEX = 1;
    private static final int FIRST_COLUMN = 1;
    private static final String ROOM_ID_COLUMN_NAME = "roomId";
    private final ConnectionSetup con;

    public RoomDAO() {
        con = new ConnectionSetup();
    }

    public long addRoom(String roomName) throws SQLException {
        String query = "INSERT INTO room (roomName) VALUES (?)";
        PreparedStatement pstmt = con.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(FIRST_PARAMETER_INDEX, roomName);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getLong(FIRST_COLUMN);
        }
        throw new SQLException("아무 값도 삽입되지 않았습니다.");
    }

    public long findRoomIdByName(String roomName) throws SQLException {
        String query = "SELECT roomId FROM room WHERE roomName = ? ORDER BY roomId DESC";
        PreparedStatement pstmt = con.getConnection().prepareStatement(query);
        pstmt.setString(FIRST_PARAMETER_INDEX, roomName);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("아무 값도 조회되지 않았습니다.");
        }
        return rs.getLong(ROOM_ID_COLUMN_NAME);
    }
}
