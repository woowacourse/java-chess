package chess.dao;

import chess.dto.PieceRequestDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChessDao {
    public Connection getConnection() {
        final String server = "localhost:3306";                                   // MySQL 서버 주소
        final String database = "mydb";                                           // MySQL DATABASE 이름
        final String option = "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        final String userName = "da-nyee";                                        //  MySQL 서버 아이디
        final String password = "1234";                                           // MySQL 서버 비밀번호

        Connection con = null;

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

    public void initializePieceStatus(final PieceRequestDto pieceRequestDto) throws SQLException {
        String query = "INSERT INTO piece_status (piece_name, piece_position) VALUE (?, ?)";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.setString(1, pieceRequestDto.getPieceName());
        psmt.setString(2, pieceRequestDto.getPiecePosition());
        psmt.executeUpdate();
    }

    public void initializeTurn() throws SQLException {
        String query = "INSERT INTO turn (current_turn) VALUE (?)";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.setString(1, "white");
        psmt.executeUpdate();
    }

    public void removeAllPieces() throws SQLException {
        String query = "DELETE FROM piece_status";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.executeUpdate();
    }
    
    public void removeTurn() throws SQLException {
        String query = "DELETE FROM turn";
        PreparedStatement pstm = getConnection().prepareStatement(query);
        pstm.executeUpdate();
    }
}
