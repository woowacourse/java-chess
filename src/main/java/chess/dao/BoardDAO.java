package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BoardDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "board"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "hwangbo"; // MySQL 서버 비밀번호

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

    public void addPiece(Position position, Piece piece) throws SQLException {
        String query = "INSERT INTO board VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, position.name());
        pstmt.setString(2, piece.name());
        pstmt.executeUpdate();
    }

    public void addBoard(Map<Position, Piece> board) throws SQLException {
        for (Position position : board.keySet()) {
            this.addPiece(position, board.get(position));
        }
    }

    public Map<String, String> showPieces() throws SQLException {
        String query = "SELECT * FROM board";
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        Map<String, String> board = new HashMap<>();
        while (rs.next()) {
            board.put(rs.getString("position"), rs.getString("piece"));
        }

        return board;
    }

    public void deleteBoard() throws SQLException {
        String query = "TRUNCATE board";
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        pstmt.executeUpdate();
    }
}