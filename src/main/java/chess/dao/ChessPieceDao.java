package chess.dao;

import chess.dto.PieceDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessPieceDao {

    private static final String QUERY_FOR_ADD_PIECE = "INSERT INTO piece (point,color,type) VALUES(?,?,?)";
    private static final String QUERY_FOR_SET_AUTO_INCREMENT = "ALTER TABLE piece AUTO_INCREMENT=1";
    private static final String QUERY_FOR_FIND_PIECE_BY_ID = "SELECT point, color, type FROM piece WHERE id=?";
    private static final String QUERY_FOR_UPDATE_PIECE = "UPDATE piece SET color=?, type=? WHERE point=?";
    private static final String QUERY_FOR_DELETE_PIECE_BY_POINT = "DELETE FROM piece WHERE point=?";
    private static final String QUERY_FOR_DELETE_PIECE_BY_ID = "DELETE FROM piece WHERE id=?";

    private final DataSource dataSource;

    public ChessPieceDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addPiece(String point, String color, String type) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_ADD_PIECE);
            pstmt.setString(1, point);
            pstmt.setString(2, color);
            pstmt.setString(3, type);
            pstmt.executeUpdate();
        }
    }

    public void addPiece(PieceDto pieceDto) throws SQLException {
        setAutoIncrement();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_ADD_PIECE);
            pstmt.setString(1, pieceDto.getPoint());
            pstmt.setString(2, pieceDto.getColor());
            pstmt.setString(3, pieceDto.getType());
            pstmt.executeUpdate();
        }
    }

    private void setAutoIncrement() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_SET_AUTO_INCREMENT);
            pstmt.executeUpdate();
        }
    }

    public PieceDto findPieceById(String id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_FIND_PIECE_BY_ID);
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
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_UPDATE_PIECE);
            pstmt.setString(1, sourceColor);
            pstmt.setString(2, sourceType);
            pstmt.setString(3, targetPoint);
            pstmt.executeUpdate();
        }
    }

    public void updatePiece(PieceDto sourcePieceDto, PieceDto targetPieceDto) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_UPDATE_PIECE);
            pstmt.setString(1, sourcePieceDto.getColor());
            pstmt.setString(2, sourcePieceDto.getType());
            pstmt.setString(3, targetPieceDto.getPoint());
            pstmt.executeUpdate();
        }
    }

    public void deletePieceByPoint(String point) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_DELETE_PIECE_BY_POINT);
            pstmt.setString(1, point);
            pstmt.executeUpdate();
        }
    }

    public void deletePieceById(String id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_DELETE_PIECE_BY_ID);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
