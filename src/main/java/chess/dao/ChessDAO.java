package chess.dao;

import chess.dto.RoomDTO;
import chess.dto.RoomsDTO;

import java.sql.*;
import java.util.ArrayList;

public class ChessDAO {
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


    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void createChessGame(String chessGameData) throws SQLException {
        String query = "INSERT INTO game(data) VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, chessGameData);
        pstmt.executeUpdate();
    }

    public void saveChessGame(String gameId, String chessGameData) throws SQLException {
        String query = "UPDATE game SET data = ? WHERE game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, chessGameData);
        pstmt.setString(2, gameId);
        pstmt.executeUpdate();
    }

    public String loadChessGame(String gameId) throws SQLException {
        String query = "SELECT * FROM game WHERE game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return rs.getString("data");
    }

    public RoomsDTO createRoom(String name, String pw) throws SQLException {
        String query = "SELECT * FROM game ORDER BY game_id DESC LIMIT 1";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        int gameID = -1;
        if (rs.next()) {
            gameID = rs.getInt("game_id");
        }

        query = "INSERT INTO room(room_name,room_pw,game_id) VALUES (?,?,?)";
        pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, pw);
        pstmt.setInt(3, gameID);
        pstmt.executeUpdate();

        return getTotalRoom();
    }

    public RoomsDTO getTotalRoom() throws SQLException {
        String query = "SELECT * FROM room";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        ArrayList<RoomDTO> roomDTOs = new ArrayList<>();

        while (rs.next()) {
            roomDTOs.add(new RoomDTO(rs.getInt("room_id"), rs.getString("room_name"), rs.getString("room_pw")));
        }

        return new RoomsDTO(roomDTOs);
    }

    public RoomDTO findRoomFromId(String id) throws SQLException {
        String query = "SELECT * FROM room WHERE room_id = " + id;
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);
        RoomDTO roomDTO = null;
        if (rs.next()) {
            roomDTO = new RoomDTO(rs.getInt("room_id"),
                    rs.getString("room_name"),
                    rs.getString("room_pw"),
                    rs.getString("game_id"));
        }

        return roomDTO;
    }
}
