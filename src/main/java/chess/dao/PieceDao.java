package chess.dao;

import chess.dto.PieceDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {
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

    public void addPieces(List<PieceDto> pieces, int chessGameId) throws SQLException {
        String query = "INSERT INTO PIECE(color, name, position, chessGameId) VALUE (?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        for (PieceDto piece : pieces) {
            pstmt.setString(1, piece.getColor());
            pstmt.setString(2, piece.getName());
            pstmt.setString(3, piece.getPosition());
            pstmt.setInt(4, chessGameId);
            pstmt.executeUpdate();
        }
        closeConnection(getConnection());
    }

    public List<PieceDto> findAllPiecesByChessGameId(int chessGameId) throws SQLException {
        String query = "SELECT color, name, position FROM PIECE WHERE chessGameId = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, chessGameId);
        ResultSet rs = pstmt.executeQuery();

        List<PieceDto> pieces = new ArrayList<>();
        while (rs.next()) {
            PieceDto pieceDto = new PieceDto(rs.getString("color"),
                    rs.getString("name"), rs.getString("position"));
            pieces.add(pieceDto);
        }
        closeConnection(getConnection());
        return pieces;
    }

    public void updatePiecePositionByChessGameId(int chessGameId, PieceDto pieceDto) throws SQLException {
        String query = "UPDATE PIECE SET color = ?, name = ? WHERE position = ? and chessGameId = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, pieceDto.getColor());
        pstmt.setString(2, pieceDto.getName());
        pstmt.setString(3, pieceDto.getPosition());
        pstmt.setInt(4, chessGameId);
        pstmt.executeUpdate();
        closeConnection(getConnection());
    }
}
