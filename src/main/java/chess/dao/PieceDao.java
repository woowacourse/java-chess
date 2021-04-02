package chess.dao;

import chess.domain.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class PieceDao {
    private final Connection con;

    public PieceDao() {
        con = ConnectionSetup.getConnection();
    }

//    public Map<Position, Piece> load() throws SQLException {
//        final String query = "SELECT * FROM pieces";
//        final PreparedStatement pstmt = con.prepareStatement(query);
//        final ResultSet rs = pstmt.executeQuery();
//
//        if (!rs.next()) {
//            return null;
//        }
//
//        final Map<Position, Piece> newPieces = new TreeMap<>();
//        do {
//            final String positionValue = rs.getString("position");
//            final Position position = Position.from(positionValue);
//            final String name = rs.getString("name");
//            newPieces.put(position, );
//        } while (rs.next());
//        return newPieces;
//    }

    public void clear(final Pieces pieces) throws SQLException {
        delete("pieces");
        for (final Piece piece : pieces.toList()) {
            String position = piece.getPosition().column().value() + piece.getPosition().row().value();
            savePiece(position, piece);
        }
        initTurn();
    }

    public void savePiece (final String position, final Piece piece) throws SQLException {
        final String query = "INSERT INTO pieces VALUES (?, ?)";
        final PreparedStatement pstmt = con.prepareStatement(query);
        String displayName = piece.display().toUpperCase();
        if (piece.isSameColor(Color.BLACK)) {
            pstmt.setString(1, position);
            pstmt.setString(2, "B" + displayName);
        } else {
            pstmt.setString(1, position);
            pstmt.setString(2, "W" + displayName);
        }
        pstmt.executeUpdate();
    }

    public void initTurn() throws SQLException {
        delete("game");
        final String saveQuery = "INSERT INTO game VALUES (?)";
        final PreparedStatement savePstmt = con.prepareStatement(saveQuery);
        savePstmt.setString(1, Color.WHITE.name());
        savePstmt.executeUpdate();
    }

    private void delete(String tableName) throws SQLException {
        final String query = "TRUNCATE TABLE " + tableName;
        final PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.executeUpdate();
    }

    public void updateTurn(Turn turn) throws SQLException {
        String query = "UPDATE game SET turn = ? WHERE turn = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(2, turn.player().getColor().name());
        pstmt.setString(1, turn.nextPlayer().getColor().name());
        pstmt.executeUpdate();
    }
}
