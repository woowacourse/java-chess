package chess.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChessPieceDao {

    private final DataSource dataSource;

    public ChessPieceDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addPiece(String point, String color, String type) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "INSERT INTO piece (point,color,type) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, point);
            pstmt.setString(2, color);
            pstmt.setString(3, type);
            pstmt.executeUpdate();
        }
    }

    public void updatePiece(String targetPoint, String sourceColor, String sourceType) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "UPDATE piece SET color=?, type=? WHERE point=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, sourceColor);
            pstmt.setString(2, sourceType);
            pstmt.setString(3, targetPoint);
            pstmt.executeUpdate();
        }
    }

    public void deletePiece(String point) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "DELETE FROM piece WHERE point=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, point);
            pstmt.executeUpdate();
        }
    }
}
