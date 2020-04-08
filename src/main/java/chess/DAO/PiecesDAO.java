package chess.DAO;

import chess.piece.Piece;
import chess.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PiecesDAO {

    private final Connection conn;

    public PiecesDAO(Connection conn) {
        this.conn = conn;
    }

    public Map<Position, Piece> load() throws SQLException {
        String query = "SELECT * FROM pieces";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Map<Position, Piece> pieces = new HashMap<>();
        Position position;
        Piece piece;
        do {
            position = Position.of(rs.getString("position"));
            char symbol = rs.getString("piece").charAt(0);
            boolean hasMoved = rs.getBoolean("hasMoved");
            piece = Piece.of(symbol, hasMoved);
            pieces.put(position, piece);
        } while (rs.next());

        return pieces;
    }

    public void save(Map<Position, Piece> pieces) throws SQLException {
        for (Position position : pieces.keySet()) {
            savePiece(position, pieces.get(position));
        }
    }

    private void savePiece(Position position, Piece piece) throws SQLException {
        String query = "UPDATE pieces SET piece = ?, hasMoved = ? WHERE position = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, Character.toString(piece.getSymbol()));
        pstmt.setBoolean(2, piece.getHasMoved());
        pstmt.setString(3, position.getKey());
        pstmt.executeUpdate();
    }
}
