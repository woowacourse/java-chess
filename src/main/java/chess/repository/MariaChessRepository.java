package chess.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaChessRepository {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "woowa-chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        try {
            Class.forName("org.mariadb.jdbc.Driver");
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

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

//
//    public void addUser(User user) throws SQLException {
//        String query = "INSERT INTO user VALUES (?, ?)";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, user.getUserId());
//        pstmt.setString(2, user.getName());
//        pstmt.executeUpdate();
//    }
//
//    public User findByUserId(String userId) throws SQLException {
//        String query = "SELECT * FROM user WHERE user_id = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, userId);
//        ResultSet rs = pstmt.executeQuery();
//
//        if (!rs.next()) return null;
//
//        return new User(
//                rs.getString("user_id"),
//                rs.getString("name"));
//    }
//
//    public void deleteAll() throws SQLException {
//        String query = "DELETE FROM user";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.executeUpdate();
//    }
//
//    public int updateUser(String id, String name) throws SQLException {
//        String query = "UPDATE user SET name = ? WHERE user_id = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, name);
//        pstmt.setString(2, id);
//        return pstmt.executeUpdate();
//    }

}
