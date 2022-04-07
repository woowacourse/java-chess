package chess.dao;

import chess.domain.Color;
import chess.domain.PieceConverter;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.util.DBConnection;
import chess.util.JdbcTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PieceDao {

    private final JdbcTemplate jdbcTemplate;

    public PieceDao(Connection connection) {
        this.jdbcTemplate = new JdbcTemplate(connection);
    }

    public PieceDao() {
        this(DBConnection.getConnection());
    }

    public Map<Position, Piece> findAllPieces() {
        final String query = "select type, color, position_col, position_row from piece";

        return jdbcTemplate.executeSelect(connection -> connection.prepareStatement(query), this::piecesMapper);
    }

    private Map<Position, Piece> piecesMapper(ResultSet resultSet) throws SQLException {
        Map<Position, Piece> pieces = new HashMap<>();
        while (resultSet.next()) {
            String type = resultSet.getString("type");
            String colorName = resultSet.getString("color");
            char column = resultSet.getString("position_col").charAt(0);
            char row = resultSet.getString("position_row").charAt(0);

            Position position = Position.of(column, row);
            Color color = Color.valueOf(colorName);
            pieces.put(position, PieceConverter.parseToPiece(type, color));
        }
        return pieces;
    }

    public void savePieces(Map<Position, Piece> chessBoard) {
        final String query = "insert into piece (type, color, position_col, position_row) values (?, ?, ?, ?)";

        jdbcTemplate.executeBatch(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(query);
            for (Position position : chessBoard.keySet()) {
                Piece piece = chessBoard.get(position);
                pstmt.setString(1, piece.name());
                pstmt.setString(2, piece.color().name());
                pstmt.setString(3, String.valueOf(position.column()));
                pstmt.setString(4, String.valueOf(position.row()));
                pstmt.addBatch();
            }
            return pstmt;
        });
    }

    public void updatePiecePosition(Position position, Position movePosition) {
        final String query = "update piece set position_col = ?, position_row = ? "
                + "where position_col = ? and position_row = ?";
        jdbcTemplate.executeUpdate(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, String.valueOf(movePosition.column()));
            pstmt.setString(2, String.valueOf(movePosition.row()));
            pstmt.setString(3, String.valueOf(position.column()));
            pstmt.setString(4, String.valueOf(position.row()));
            return pstmt;
        });
    }

    public void updatePiece(Position position, Piece changePiece) {
        final String query = "update piece set type = ? "
                + "where position_col = ? and position_row = ?";

        jdbcTemplate.executeUpdate(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, changePiece.name());
            pstmt.setString(2, String.valueOf(position.column()));
            pstmt.setString(3, String.valueOf(position.row()));
            return pstmt;
        });
    }

    public void deletePiece(Position position) {
        final String query = "delete from piece where position_col = ? and position_row = ?";

        jdbcTemplate.executeUpdate(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, String.valueOf(position.column()));
            pstmt.setString(2, String.valueOf(position.row()));
            return pstmt;
        });
    }

    public void deleteAllPiece() {
        final String query = "delete from piece";

        jdbcTemplate.executeUpdate(connection -> connection.prepareStatement(query));
    }
}
