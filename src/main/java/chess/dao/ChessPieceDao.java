package chess.dao;

import chess.dto.PieceDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void addPiece(PieceDto pieceDto) throws SQLException {
        setAutoIncrement();
        try (Connection conn = dataSource.getConnection()) {
            String query = "INSERT INTO piece (point,color,type) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, pieceDto.getPoint());
            pstmt.setString(2, pieceDto.getColor());
            pstmt.setString(3, pieceDto.getType());
            pstmt.executeUpdate();
        }
    }

    private void setAutoIncrement() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "ALTER TABLE piece AUTO_INCREMENT=1";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        }
    }

    public PieceDto findPieceById(String id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT point, color, type FROM piece WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) return null;
                PieceDto piece = new PieceDto();
                piece.setPoint(rs.getString("point"));
                piece.setColor(rs.getString("color"));
                piece.setType(rs.getString("type"));
                return piece;
            }
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

    public void updatePiece(PieceDto sourcePieceDto, PieceDto targetPieceDto) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "UPDATE piece SET color=?, type=? WHERE point=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, sourcePieceDto.getColor());
            pstmt.setString(2, sourcePieceDto.getType());
            pstmt.setString(3, targetPieceDto.getPoint());
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

    public void deletePieceById(String id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "DELETE FROM piece WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
