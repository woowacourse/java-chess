package chess.domain.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void addPosition(ChessBoardDTOForDAO eachPosition) throws SQLException {

        String query = "INSERT INTO chessTable (game_id, position, teamColor, pieceType, alive) VALUES (1, ?, ?, ?, ?)";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, eachPosition.getPosition());
        pstmt.setString(2, eachPosition.getTeamColor());
        pstmt.setString(3, eachPosition.getPieceType());
        pstmt.setString(4, eachPosition.getAlive());

        pstmt.executeUpdate();

    }

    public void addPositions(List<ChessBoardDTOForDAO> board) throws SQLException {
        for (ChessBoardDTOForDAO eachPosition : board) {
            addPosition(eachPosition);
        }
    }


    public List<ChessBoardDTOForDAO> findByGameId(String gameId) throws SQLException {
        String query = "SELECT * FROM chessTable WHERE game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet rs = pstmt.executeQuery();
        List<ChessBoardDTOForDAO> chessboard = new ArrayList<>();
        while (rs.next()) {
            ChessBoardDTOForDAO chessboardDto = new ChessBoardDTOForDAO();
            chessboard.add(chessboardDto);
            chessboardDto.setPosition(rs.getString("position"));
            chessboardDto.setTeamColor(rs.getString("teamColor"));
            chessboardDto.setPieceType(rs.getString("pieceType"));
            chessboardDto.setAlive(rs.getString("alive"));
        }
        return chessboard;
    }

    public void removePositions() throws SQLException {
        String query = "DELETE FROM chessTable WHERE game_id=1";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

}
