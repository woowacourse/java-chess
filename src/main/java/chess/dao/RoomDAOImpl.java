package chess.dao;

import chess.domain.Room;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    @Override
    public void create(final String roomReqData, final int gameId) {
        String query = "INSERT INTO room(room_name,room_pw,game_id) VALUES (?,?,?)";
        JSONObject roomData = new JSONObject(roomReqData);
        PreparedStatement pstmt = null;
        try {
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, roomData.getString("name"));
            pstmt.setString(2, roomData.getString("pw"));
            pstmt.setInt(3, gameId);
            pstmt.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new IllegalArgumentException("방을 만들지 못했습니다.");
        }
    }

    @Override
    public Room readRoomFromId(final String roomId) {
        String query = "SELECT * FROM room WHERE room_id = " + roomId;
        PreparedStatement pstmt = null;
        try {
            pstmt = getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery(query);
            rs.next();
            return new Room(rs.getInt("room_id"),
                    rs.getString("room_name"),
                    rs.getString("room_pw"),
                    rs.getString("game_id"));
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new IllegalArgumentException("방 정보를 가져오지 못했습니다.");
        }
    }

    @Override
    public List<Room> readTotalRoom() {
        String query = "SELECT * FROM room";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery(query);
            return generateRooms(rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new IllegalArgumentException("방 정보들을 가져오지 못했습니다.");
        }
    }

    private List<Room> generateRooms(ResultSet rs) throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        while (rs.next()) {
            rooms.add(new Room(rs.getInt("room_id"),
                    rs.getString("room_name"),
                    rs.getString("room_pw"),
                    rs.getString("game_id")));
        }
        return rooms;
    }
}
