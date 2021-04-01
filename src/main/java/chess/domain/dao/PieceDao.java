package chess.domain.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public final class PieceDao {

    public Map<Position, Piece> load() throws SQLException {
        try (final Connection conn = ConnectionSetup.getConnection()) {
            final String query = "SELECT * FROM pieces";
            final PreparedStatement pstmt = conn.prepareStatement(query);
            final ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            final Map<Position, Piece> pieces = new TreeMap<>();
            do {
                final String positionValue = rs.getString("position");
                final Position position = Position.from(positionValue);
                final String name = rs.getString("name");
                pieces.put(position, PieceFactory.correctPiece(name));
            } while (rs.next());
            return pieces;
        }
    }

    public void save(final Map<Position, Piece> pieces) throws SQLException {
        deleteAll();
        for (final Position position : pieces.keySet()) {
            savePiece(position, pieces.get(position));
        }
    }

    public void savePiece(final Position position, final Piece piece) throws SQLException {
        try (final Connection conn = ConnectionSetup.getConnection()) {
            final String query = "INSERT INTO pieces VALUES (?, ?)";
            final PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, position.horizontalSymbol() + position.verticalSymbol());
            pstmt.setString(2, piece.name());
            pstmt.executeUpdate();
        }
    }

    public void deleteAll() throws SQLException {
        try (final Connection conn = ConnectionSetup.getConnection()) {
            final String query = "DELETE FROM pieces";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        }
    }
}
