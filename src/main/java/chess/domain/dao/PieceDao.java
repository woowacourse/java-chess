package chess.domain.dao;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PieceDao {

    private static final Map<String, Piece> PIECES = new HashMap<>();

    static {
        PIECES.put("p", new Pawn(Team.WHITE));
        PIECES.put("P", new Pawn(Team.BLACK));
        PIECES.put("q", new Queen(Team.WHITE));
        PIECES.put("Q", new Queen(Team.BLACK));
        PIECES.put("r", new Rook(Team.WHITE));
        PIECES.put("R", new Rook(Team.BLACK));
        PIECES.put("b", new Bishop(Team.WHITE));
        PIECES.put("B", new Bishop(Team.BLACK));
        PIECES.put("k", new King(Team.WHITE));
        PIECES.put("K", new King(Team.BLACK));
        PIECES.put("n", new Knight(Team.WHITE));
        PIECES.put("N", new Knight(Team.BLACK));
        PIECES.put(".", Blank.getInstance());
    }

    private final Connection conn;

    public PieceDao() {
        conn = ConnectionSetup.getConnection();
    }

    public Map<Position, Piece> load() throws SQLException {
        final String query = "SELECT * FROM pieces";
        final PreparedStatement pstmt = conn.prepareStatement(query);
        final ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        final Map<Position, Piece> pieces = new TreeMap<>();
        do {
            final String positionValue = rs.getString("position");
            final Position position = new Position(positionValue.split("")[0],
                positionValue.split("")[1]);
            final String name = rs.getString("name");
            pieces.put(position, PIECES.get(name));
        } while (rs.next());

        return pieces;
    }

    public void save(final Map<Position, Piece> pieces) throws SQLException {
        deleteAll();
        for (final Position position : pieces.keySet()) {
            savePiece(position, pieces.get(position));
        }
    }

    private void savePiece(final Position position, final Piece piece) throws SQLException {
        String query = "INSERT INTO pieces VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, position.horizontal().symbol() + position.vertical().symbol());
        pstmt.setString(2, piece.name());
        pstmt.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM pieces";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.executeUpdate();
    }
}
