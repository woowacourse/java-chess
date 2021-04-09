package chess.dao;

import chess.entity.Chess;

import java.sql.*;

public class ChessDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_name"; // MySQL DATABASE 이름
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

    public void addChess(Chess chess) throws SQLException {
        String query = "INSERT INTO chess VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, chess.getChessId());
        pstmt.setString(2, chess.getChess());
        pstmt.setString(3, chess.getTurn());
        pstmt.executeUpdate();
    }

    public Chess findByChessId(String chessId) throws SQLException {
        String query = "SELECT * FROM chess WHERE chess_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, chessId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return new Chess(
                rs.getString("chess_id"),
                rs.getString("chess"),
                rs.getString("turn"))
                ;
    }

    public void updateChess(Chess chess, String updateChess, String updateTurn) throws SQLException {
        String query = "UPDATE chess SET chess = ?, turn = ? WHERE chess_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, updateChess);
        pstmt.setString(2, updateTurn);
        pstmt.setString(3, chess.getChessId());
        pstmt.executeUpdate();
    }

    public void deleteChess(Chess chess) throws SQLException {
        String query = "DELETE FROM chess WHERE chess_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, chess.getChessId());
        pstmt.executeUpdate();
    }

    public void deleteAllChess() throws SQLException {
        String query = "DELETE FROM chess";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}