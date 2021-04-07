package web.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    public static final int NO_ROOM_NUMBER = 0;

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "chess_db";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public int newRoomId() throws SQLException {
        int roomId = NO_ROOM_NUMBER;
        try (Connection con = getConnection()) {
            String query = "INSERT INTO chessRoom() VALUES()";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();
            query = "SELECT LAST_INSERT_ID()";
            pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomId = rs.getInt(1);
            }
        }

        return roomId;
    }

    public List<Integer> findRoomIds() throws SQLException {
        List<Integer> roomIds = new ArrayList<>();
        try (Connection con = getConnection()) {
            String query = "SELECT room_id FROM chessRoom";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                roomIds.add(rs.getInt("room_id"));
            }
        }

        return roomIds;
    }
}
