package chess.dao;

import chess.domain.game.Game;
import chess.dto.GameStateDto;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class GameDao {

    public GameDao() {

    }

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess_db"; // MySQL DATABASE 이름
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
            con = DriverManager
                    .getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
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

    public void addGame(String gameId, GameStateDto gameStateDto) throws SQLException {
        String query = "INSERT INTO games VALUES (?, ?)";
        Gson gson = new Gson();
        String gameInfo = gson.toJson(gameStateDto);
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameId);
            pstmt.setString(2, gameInfo);
            pstmt.executeUpdate();
        }
    }

    public void updateGame(String gameId, GameStateDto gameStateDto) throws SQLException {
        String query = "UPDATE games SET game_info = ? WHERE game_id = ?";
        Gson gson = new Gson();
        String gameInfo = gson.toJson(gameStateDto);
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameInfo);
            pstmt.setString(2, gameId);
            pstmt.executeUpdate();
        }
    }

    public Game findGameByGameId(String gameId) throws SQLException {
        String query = "SELECT * FROM games WHERE game_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameId);
            String gameInfo = "";
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                gameInfo = rs.getString("game_info");
            }
            return GameSerializer.deserialize(gameInfo);
        }
    }

    public void deleteGame(String gameId) throws SQLException {
        String query = "DELETE FROM games WHERE game_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameId);
            pstmt.executeUpdate();
        }
    }
}
