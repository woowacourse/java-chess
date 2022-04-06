package chess.dao;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class PieceDaoImpl implements PieceDao {

    private final ConnectionSetup connectionSetup;

    public PieceDaoImpl(ConnectionSetup connectionSetup) {
        this.connectionSetup = connectionSetup;
    }

    @Override
    public void save(long gameId, Position position, Piece piece) {
        String query = "INSERT INTO piece (game_id, position, piece_type, color) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, gameId);
            pstmt.setString(2, convertPositionToString(position));
            pstmt.setString(3, piece.getPieceType().name());
            pstmt.setString(4, piece.getColor().name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String convertPositionToString(Position position) {
        Column column = position.getColumn();
        Row row = position.getRow();
        return column.name().toLowerCase(Locale.ROOT) + row.getValue();
    }

    @Override
    public List<Piece> findAll(long gameId) {
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = createFindAllPreparedStatement(connection, gameId);
             ResultSet rs = pstmt.executeQuery()) {
            return getPieces(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private PreparedStatement createFindAllPreparedStatement(Connection connection, long gameId)
            throws SQLException {
        String query = "SELECT * FROM piece WHERE game_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setLong(1, gameId);
        return pstmt;
    }

    private List<Piece> getPieces(ResultSet rs) throws SQLException {
        List<Piece> pieces = new ArrayList<>();
        while (rs.next()) {
            PieceType pieceType = PieceType.valueOf(rs.getString("piece_type"));
            Color color = Color.valueOf(rs.getString("color"));
            pieces.add(pieceType.createPiece(color));
        }
        return pieces;
    }

    @Override
    public Optional<Piece> find(long gameId, Position position) {
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = createFindPreparedStatement(connection, gameId, position);
             ResultSet rs = pstmt.executeQuery()) {
            return getPiece(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private PreparedStatement createFindPreparedStatement(Connection connection, long gameId, Position position)
            throws SQLException {
        String query = "SELECT * FROM piece WHERE game_id = ? AND position = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setLong(1, gameId);
        pstmt.setString(2, convertPositionToString(position));
        return pstmt;
    }

    private Optional<Piece> getPiece(ResultSet rs) throws SQLException {
        if (rs.next()) {
            PieceType pieceType = PieceType.valueOf(rs.getString("piece_type"));
            Color color = Color.valueOf(rs.getString("color"));
            return Optional.of(pieceType.createPiece(color));
        }
        return Optional.empty();
    }

    @Override
    public void updatePosition(long gameId, Position start, Position target) {
        String query = "UPDATE piece SET position = ? WHERE game_id = ? AND position = ?";
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, convertPositionToString(target));
            pstmt.setLong(2, gameId);
            pstmt.setString(3, convertPositionToString(start));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long gameId, Position position) {
        String query = "DELETE FROM piece WHERE game_id = ? AND position = ?";
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, gameId);
            pstmt.setString(2, convertPositionToString(position));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(long gameId) {
        String query = "DELETE FROM piece WHERE game_id = ?";
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, gameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
