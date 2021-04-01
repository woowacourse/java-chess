package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessLogDao {
    private static final String MOVE = "move ";
    private static final String DELIMITER = " ";

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
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

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addLog(String roomId, String target, String destination) throws SQLException {
        String query = "INSERT INTO chessgame (room_id, target, destination) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, roomId);
        pstmt.setString(2, target);
        pstmt.setString(3, destination);
        pstmt.executeUpdate();
    }

    public List<String> applyCommand(String userId) throws SQLException {
        getConnection();
        String query = "SELECT target, destination FROM chess.chessgame WHERE room_id = ? ORDER BY command_date ASC;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userId);
        ResultSet rs = pstmt.executeQuery();
        List<String> commands = new ArrayList<>();

        while (rs.next()) {
            commands.add(MOVE + rs.getString(1) + DELIMITER + rs.getString(2));
        }

        return commands;
    }

    public void deleteLog(String roomId) throws SQLException {
        getConnection();
        String query = "DELETE FROM chess.chessgame WHERE room_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, roomId);
        pstmt.executeUpdate();
    }
}
