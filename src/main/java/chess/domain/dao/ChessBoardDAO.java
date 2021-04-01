package chess.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChessBoardDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_chess"; // MySQL DATABASE 이름
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

    public void addPosition(ChessBoardDTOForDAO board) throws SQLException {
        String query = "INSERT INTO chessTable (game_id, position, teamColor, pieceType, alive) VALUES (1, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, board.getPosition());
        pstmt.setString(2, board.getTeamColor());
        pstmt.setString(3, board.getPieceType());
        pstmt.setString(4, board.getAlive());
        pstmt.executeUpdate();
    }

    public void removePositions() throws SQLException {
        String query = "DELETE FROM chessTable WHERE game_id=1";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();

    }

}
