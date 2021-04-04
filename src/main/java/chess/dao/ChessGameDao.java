package chess.dao;

import chess.domain.ChessGame;
import chess.dto.ChessGameStatusDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "woowa"; // MySQL DATABASE 이름
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

    public int insertChessGameReturnId(ChessGame chessGame) throws SQLException {
        String query = "INSERT INTO CHESS_GAME(turn, isFinish) VALUE (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, chessGame.turn().name());
        pstmt.setBoolean(2, !chessGame.runnable());
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        int newChessGameId = 0;
        if (rs.next()) {
            newChessGameId = rs.getInt(1);
        }
        closeConnection(getConnection());
        return newChessGameId;
    }

    public List<Integer> selectAllChessGameId() throws SQLException {
        String query = "SELECT * FROM CHESS_GAME";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<Integer> chessGameIds = new ArrayList<>();
        while (rs.next()) {
            chessGameIds.add(rs.getInt("chessGameId"));
        }
        closeConnection(getConnection());
        return chessGameIds;
    }

    public ChessGameStatusDto findChessGameStateById(int chessGameId) throws SQLException {
        String query = "SELECT turn, IF(isFinish, 'true', 'false') isFinish FROM CHESS_GAME WHERE chessGameId = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, chessGameId);
        ResultSet rs = pstmt.executeQuery();
        closeConnection(getConnection());

        if (!rs.next()) return null;

        return new ChessGameStatusDto(rs.getString("turn"), rs.getBoolean("isFinish"));
    }

    public void updateChessGameStateById(int chessGameId, String turn, boolean isFinish) throws SQLException {
        String query = "UPDATE CHESS_GAME SET turn = ?, isFinish = ? WHERE chessGameId = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, turn);
        pstmt.setBoolean(2, isFinish);
        pstmt.setInt(3, chessGameId);
        pstmt.executeUpdate();
        closeConnection(getConnection());
    }

    public void deleteChessGameById(int chessGameId) throws SQLException {
        String query = "DELETE FROM CHESS_GAME WHERE chessGameId = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, chessGameId);
        pstmt.executeUpdate();
        closeConnection(getConnection());
    }

    public void insertChessGameById(int chessGameId, String turn, boolean isFinish) throws SQLException {
        String query = "INSERT INTO CHESS_GAME VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, chessGameId);
        pstmt.setString(2, turn);
        pstmt.setBoolean(3, isFinish);
        pstmt.executeUpdate();
        closeConnection(getConnection());
    }
}
