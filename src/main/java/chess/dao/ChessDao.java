package chess.dao;

import chess.dto.PlayerIdsDto;
import chess.dto.CommandsDto;
import chess.dto.CreateRequestDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessDao {

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_name"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
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
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
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
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public List<String> gameNames() throws SQLException {
        String query = "SELECT game_name FROM game ORDER BY game_id DESC";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("game_name"));
        }

        return ids;
    }

    public void createGameByName(final CreateRequestDto createRequestDto) throws SQLException {
        String query = "INSERT INTO game(game_name) VALUES(?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, createRequestDto.getGameName());
        pstmt.executeUpdate();
    }

    public void insertStartCommand(final String gameName) throws SQLException {
        String query = "INSERT INTO history(game_id, command) VALUES(?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, findGameIdByName(gameName));
        pstmt.setString(2, "start");
        pstmt.executeUpdate();
    }

    public void insertMoveCommand(final String source, final String target, final String gameName)
        throws SQLException {
        String query = "INSERT INTO history(game_id, command) VALUES(?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, findGameIdByName(gameName));
        pstmt.setString(2, String.format("move %s %s", source, target));
        pstmt.executeUpdate();
    }

    public void updatePlayerIds(final PlayerIdsDto playerIdsDto, final String gameName) throws SQLException {
        String query = "UPDATE game SET white_user=?, black_user=? WHERE game_id=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, playerIdsDto.getWhiteUserId());
        pstmt.setString(2, playerIdsDto.getBlackUserId());
        pstmt.setString(3, findGameIdByName(gameName));
        pstmt.executeUpdate();
    }

    private String findGameIdByName(final String gameName) throws SQLException {
        String query = "SELECT game_id FROM game WHERE game_name=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameName);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            throw new SQLException();
        }

        return rs.getString("game_id");
    }

    public CommandsDto findCommandsByName(final String gameName) throws SQLException {
        String query = "SELECT command FROM history WHERE game_id=? ORDER BY history_id";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, findGameIdByName(gameName));
        ResultSet rs = pstmt.executeQuery();

        List<String> commands = new ArrayList<>();
        while(rs.next()) {
            commands.add(rs.getString("command"));
        }

        return new CommandsDto(commands);
    }
}
