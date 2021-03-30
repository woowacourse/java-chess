package chess.dao;

import java.sql.*;

public class ChessDAO {
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

    public void addChessGame(String gameId, String data) throws SQLException {
        String query = "INSERT INTO chess VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        pstmt.setString(2, data);
        pstmt.executeUpdate();
    }

    public void updateChessGame(String gameId, String data) throws SQLException {
        String query = "UPDATE chess SET data=? WHERE game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, data);
        pstmt.setString(2, gameId);
        pstmt.execute();
    }

    public String findChessGameByGameId(String gameId) throws SQLException {
        String query = "SELECT data FROM chess WHERE game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();

        return resultSet.getString("data");
    }

    public boolean isExistGameId(String gameId) throws SQLException {
        String query = "SELECT count(*) as count FROM chess WHERE game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();

        return resultSet.getInt("count") != 0;
    }

}
