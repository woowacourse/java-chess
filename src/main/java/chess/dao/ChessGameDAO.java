package chess.dao;

import chess.domain.game.WebChessGame;
import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDAO {

    private final BoardSerializer boardSerializer = new BoardSerializer();

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "java_chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        driverLoad();
        con = driverConnect(con, server, database, option, userName, password);
        return con;
    }

    private Connection driverConnect(Connection con, String server, String database, String option,
        String userName, String password) {
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

    private void driverLoad() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
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

    public void addGame(WebChessGame chessGame) {
        String query = "INSERT INTO chess_game (turn, finished, board) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, chessGame.getTurn());
            pstmt.setBoolean(2, chessGame.isOver());
            pstmt.setString(
                3,
                boardSerializer.boardSerialize(chessGame.getChessBoardMap())
            );
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public int recentGame() throws SQLException {
        String query = "SELECT MAX(id) FROM chess_game";
        try (ResultSet rs = getConnection().createStatement().executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("MAX(id)");
            }
            return -1;
        }
    }

    public void saveGame(int gameId, WebChessGame chessGame) {
        String query = "UPDATE chess_game SET turn = ?, finished = ?, board = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, chessGame.getTurn());
            pstmt.setBoolean(2, chessGame.isOver());
            pstmt.setString(
                3,
                boardSerializer.boardSerialize(chessGame.getChessBoardMap())
            );
            pstmt.setInt(4, gameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public WebChessGame loadGame(int gameId) throws SQLException {
        String query = "SELECT * FROM chess_game WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, gameId);
        ResultSet rs = pstmt.executeQuery();
        try (pstmt; rs) {
            if (!rs.next()) {
                return null;
            }
            return new WebChessGame(
                boardSerializer.boardDeserialize(rs.getString("board")),
                Color.of(rs.getString("turn"))
            );
        }
    }

    public Color turn(int gameId) throws SQLException {
        String query = "SELECT turn FROM chess_game WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, gameId);
        ResultSet rs = pstmt.executeQuery();
        try (pstmt; rs) {
            if (!rs.next()) {
                return null;
            }
            return Color.of(rs.getString("turn"));
        }
    }

    public void finish(int gameId) {
        String query = "UPDATE chess_game SET finished = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setBoolean(1, true);
            pstmt.setInt(2, gameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean finished(int gameId) throws SQLException {
        String query = "SELECT finished FROM chess_game WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, gameId);
        ResultSet rs = pstmt.executeQuery();
        try (pstmt; rs) {
            if (!rs.next()) {
                return false;
            }
            return rs.getBoolean("finished");
        }
    }
}
