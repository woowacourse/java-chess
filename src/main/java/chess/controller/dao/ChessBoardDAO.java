package chess.controller.dao;

import java.sql.*;

public class ChessBoardDAO {
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
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option,
                    userName, password);
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

    public void addChessBoard() throws SQLException {
        String query = "INSERT INTO chessBoard VALUES()";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.executeUpdate();
        closeConnection(con);
    }

    public ChessBoard findRecentChessBoard() throws SQLException {
        String query = "SELECT * FROM chessBoard ORDER BY chessBoardId DESC limit 1";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            closeConnection(con);
            return null;
        }

        ChessBoard chessBoard = new ChessBoard(rs.getInt("chessBoardId"));
        closeConnection(con);
        return chessBoard;
    }

    public void deleteChessBoard(ChessBoard chessBoard) throws SQLException {
        if (chessBoard == null) {
            return;
        }

        String query = "DELETE FROM chessBoard WHERE chessBoardId = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, chessBoard.getChessBoardId());
        pstmt.executeUpdate();
        closeConnection(con);
    }
}
