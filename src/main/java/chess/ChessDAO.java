package chess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.User;

public class ChessDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=Asia/Seoul";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "sally118"; // MySQL 서버 비밀번호

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

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user (user_name, user_password) VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, user.getId());
        System.out.println(user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.executeUpdate();
    }

    public User findByUserId(String userId) throws SQLException {
        String query = "SELECT * FROM user WHERE user_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userId);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return null;
        return new User(rs.getString("user_name"), rs.getString("user_password"));
    }

    public User findByUserNameAndPwd(String name, String password) throws SQLException {
        String query = "select * from user where user_name = ? and user_password = ?;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return null;
        return new User(rs.getString("user_name"), rs.getString("user_password"));
    }
}