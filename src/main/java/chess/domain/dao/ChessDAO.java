package chess.domain.dao;

import chess.domain.dto.PieceDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessDAO {

    private final int SINGLE_BOARD_NUMBER  = 1;

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "chess";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addPiece(PieceDTO pieceDTO) throws SQLException {
        String query = "INSERT INTO piece(board_id, piece_kind, piece_location) VALUES(?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, SINGLE_BOARD_NUMBER);
        pstmt.setString(2, pieceDTO.getPieceKind());
        pstmt.setString(3, pieceDTO.getLocation());

        pstmt.executeUpdate();
    }

    public void deletePiece(String location, int boardNumber) throws SQLException {
        String query = "DELETE FROM piece WHERE (board_id = ?) AND (piece_location = ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, SINGLE_BOARD_NUMBER);
        pstmt.setString(2, location);

        pstmt.executeUpdate();
    }

    public void resetPiece(int boardNumber) throws SQLException {
        String query = "DELETE FROM piece WHERE board_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, SINGLE_BOARD_NUMBER);

        pstmt.executeUpdate();
    }

    public PieceDTO pieceOnLocation(String location, int boardNumber) throws SQLException {
        String query = "SELECT * FROM piece WHERE (board_id = ?) AND (piece_location = ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, SINGLE_BOARD_NUMBER);
        pstmt.setString(2, location);

        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return null;

        return new PieceDTO(
            rs.getString("piece_location"),
            rs.getString("piece_kind"));
    }
}
