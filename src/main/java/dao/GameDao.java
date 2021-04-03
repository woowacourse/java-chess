package dao;

import json.ResultDto;

import java.sql.*;

public class GameDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_name"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

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

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public int selectGameCount() throws SQLException {
        String query = "select count(*) from game";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;
        return Integer.parseInt(rs.getString(1));
    }

    public void updateGame(String jsonData) throws SQLException {
        String query = "UPDATE game SET jsondata = ? WHERE gameid=1";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, jsonData);
        pstmt.executeUpdate();
    }

    public void insertGameInfo(ResultDto resultDto) throws SQLException {
        String query = "INSERT INTO game VALUES(1, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, resultDto.getResponse());
        pstmt.executeUpdate();
    }

    public String getGameInfo() throws SQLException {
        String query = "select jsondata from game where gameid=1";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return null;
        return rs.getString(1);
    }

    public void deleteGame() throws SQLException {
        String query = "delete from game";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
