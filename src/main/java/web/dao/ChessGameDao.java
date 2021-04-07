package web.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
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

    public void addCommand(String command, int roomId) throws SQLException {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO chessGame(command, room_id) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, command);
            pstmt.setInt(2, roomId);
            pstmt.executeUpdate();
        }
    }

    public List<String> findByRoomId(int roomId) throws SQLException {
        List<String> moves = new ArrayList<>();

        try (Connection con = getConnection()) {
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
