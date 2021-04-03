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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PieceDao {
    private final Connection con;

    public PieceDao() {
        con = ConnectionSetup.getConnection();
    }

    public Map<Piece, Position> load(final Pieces pieces) throws SQLException {
        final String query = "SELECT * FROM pieces";
        final PreparedStatement pstmt = con.prepareStatement(query);
        final ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        final Map<Piece, Position> newPieces = new TreeMap<>();
        do {
            final String name = rs.getString("name");
            final String positionValue = rs.getString("position");
            final Position position = Position.from(positionValue);
            newPieces.put(pieces.findPieceByName(name), position);
        } while (rs.next());
        return newPieces;
    }

    public void clear(final Pieces pieces) throws SQLException {
        delete("pieces");
        for (final Piece piece : pieces.toList()) {
            String position = piece.getPosition().column().value() + piece.getPosition().row().value();
            savePiece(piece, position);
        }
        initTurn();
    }

    public void savePiece (final Piece piece, final String position) throws SQLException {
        final String query = "INSERT INTO pieces VALUES (?, ?)";
        final PreparedStatement pstmt = con.prepareStatement(query);
        String displayName = piece.display().toUpperCase();
        if (piece.isSameColor(Color.BLACK)) {
            pstmt.setString(1, "B" + displayName);
            pstmt.setString(2, position);
        } else {
            pstmt.setString(1, "W" + displayName);
            pstmt.setString(2, position);
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

    public void delete(String tableName) throws SQLException {
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

    public String findTurn() throws SQLException {
        String query = "SELECT * FROM game";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;
        return rs.getString("turn");
    }

    public Map<Piece, Position> findPieces(Pieces pieces) throws SQLException {
        String query = "SELECT * FROM pieces";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;
        return daoToBoard(rs.getString("name"), rs.getString("position"), pieces);
    }

    private Map<Piece, Position> daoToBoard(String piece, String position, Pieces pieces){
        Map<Piece, Position> board = new LinkedHashMap<>();
        String[] splitPiece = piece.split(",");
        String[] splitPosition = position.split(",");

        for (int i = 0; i < splitPosition.length; i++) {
            board.put(pieces.findPieceByName(splitPiece[i]), Position.from(splitPosition[i]));
        }
        return board;
    }
}
