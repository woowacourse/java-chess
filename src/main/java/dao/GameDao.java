package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ResultDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "db_name";
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

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void updateGame(String jsonData, int roomNumber) throws SQLException {
        String query = "UPDATE game SET jsondata = ? WHERE gameid=?";
        System.out.println("업데이트 쿼리문 !!! : " + query);
        System.out.println("업데이트 데이터 : " + jsonData);
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, jsonData);
        pstmt.setInt(2, roomNumber);
        pstmt.executeUpdate();
    }

    public void insertNewGameInfo(ResultDto resultDto) throws SQLException, JsonProcessingException {
        String query = "INSERT INTO game(jsondata) VALUES(?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        String value = new ObjectMapper().writeValueAsString(resultDto);
        pstmt.setString(1, value);
        pstmt.executeUpdate();
    }

    public String selectGameInfo(String roomNumber) throws SQLException {
        String query = "select jsondata from game where gameid=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, Integer.parseInt(roomNumber));
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return null;
        return rs.getString(1);
    }

    public void deleteGame(int roomNumber) throws SQLException {
        String query = "delete from game where gameid=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomNumber);
        pstmt.executeUpdate();
    }

    public List<String> findGames() throws SQLException {
        String query = "select gameid from game";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<String> gameIDs = new ArrayList<>();
        while (rs.next()) {
            gameIDs.add(rs.getString(1));
        }
        return gameIDs;
    }

    public int lastGameID() throws SQLException {
        String query = "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name ='game' AND table_schema = DATABASE()";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) throw new SQLException();
        return rs.getInt(1) - 1;
    }
}
