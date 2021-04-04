package chess.dao;

import chess.domain.Turn;
import chess.domain.piece.*;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PieceDao {
    private final Connection con;

    public PieceDao() {
        con = ConnectionSetup.getConnection();
    }

    public List<Piece> load() throws SQLException {
        final String query = "SELECT * FROM pieces";
        final PreparedStatement pstmt = con.prepareStatement(query);
        final ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        final List<Piece> newPiece = new ArrayList<>();
        while (rs.next()) {
            final String name = rs.getString("name");
            final String positionValue = rs.getString("position");
            final Position position = Position.from(positionValue);
            if ("B".equals(name.substring(0, 1))) {
                if ("R".equals(name.substring(1, 2))) {
                    newPiece.add(new Rook(Color.BLACK, position));
                }
                if ("N".equals(name.substring(1, 2))) {
                    newPiece.add(new Knight(Color.BLACK, position));
                }
                if ("B".equals(name.substring(1, 2))) {
                    newPiece.add(new Bishop(Color.BLACK, position));
                }
                if ("Q".equals(name.substring(1, 2))) {
                    newPiece.add(new Queen(Color.BLACK, position));
                }
                if ("K".equals(name.substring(1, 2))) {
                    newPiece.add(new King(Color.BLACK, position));
                }
                if ("P".equals(name.substring(1, 2))) {
                    newPiece.add(new Pawn(Color.BLACK, position));
                }
            } else {
                if ("R".equals(name.substring(1, 2))) {
                    newPiece.add(new Rook(Color.WHITE, position));
                }
                if ("N".equals(name.substring(1, 2))) {
                    newPiece.add(new Knight(Color.WHITE, position));
                }
                if ("B".equals(name.substring(1, 2))) {
                    newPiece.add(new Bishop(Color.WHITE, position));
                }
                if ("Q".equals(name.substring(1, 2))) {
                    newPiece.add(new Queen(Color.WHITE, position));
                }
                if ("K".equals(name.substring(1, 2))) {
                    newPiece.add(new King(Color.WHITE, position));
                }
                if ("P".equals(name.substring(1, 2))) {
                    newPiece.add(new Pawn(Color.WHITE, position));
                }
            }
        }
        return newPiece;
    }

    public void clear(final Pieces pieces) throws SQLException {
        delete("pieces");
        for (final Piece piece : pieces.toList()) {
            final String position = piece.getPosition().column().value() + piece.getPosition().row().value();
            savePiece(position, piece);
        }
        initTurn();
    }

    public void savePiece(final String position, final Piece piece) throws SQLException {
        final String query = "INSERT INTO pieces VALUES (?, ?)";
        final PreparedStatement pstmt = con.prepareStatement(query);
        final String displayName = piece.display().toUpperCase();
        if (piece.isSameColor(Color.BLACK)) {
            pstmt.setString(1, position);
            pstmt.setString(2, "B" + displayName);
        } else {
            pstmt.setString(1, position);
            pstmt.setString(2, "W" + displayName);
        }
        pstmt.executeUpdate();
    }

    public void deletePiece(final String position) throws SQLException {
        final String query = "DELETE FROM pieces WHERE position = ?";
        final PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, position);
        pstmt.executeUpdate();
    }

    public void initTurn() throws SQLException {
        delete("game");
        final String saveQuery = "INSERT INTO game VALUES (?)";
        final PreparedStatement savePstmt = con.prepareStatement(saveQuery);
        savePstmt.setString(1, Color.WHITE.name());
        savePstmt.executeUpdate();
    }

    public void delete(final String tableName) throws SQLException {
        final String query = "TRUNCATE TABLE " + tableName;
        final PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.executeUpdate();
    }

    public void updateTurn(final Turn turn) throws SQLException {
        delete("game");
        final String query = "INSERT INTO game VALUES (?)";
        final PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, turn.player().getColor().name());
        pstmt.executeUpdate();
    }

    public String findTurn() throws SQLException {
        final String query = "SELECT * FROM game";
        final PreparedStatement pstmt = con.prepareStatement(query);
        final ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;
        return rs.getString("turn");
    }

    public Map<Position, Piece> findPieces(final Pieces pieces) throws SQLException {
        final String query = "SELECT * FROM pieces";
        final PreparedStatement pstmt = con.prepareStatement(query);
        final ResultSet rs = pstmt.executeQuery();

        final Map<Position, Piece> result = new HashMap<>();
        while (rs.next()) {
            final Map<Position, Piece> eachCell = daoToBoard(rs.getString("position"), rs.getString("name"), pieces);
            eachCell.forEach(result::put);
        }
        return result;
    }

    private Map<Position, Piece> daoToBoard(final String position, final String piece, final Pieces pieces) {
        final Map<Position, Piece> board = new LinkedHashMap<>();
        final String[] splitPosition = position.split(",");
        final String[] splitPiece = piece.split(",");

        for (int i = 0; i < splitPosition.length; i++) {
            board.put(Position.from(splitPosition[i]), pieces.findPieceByName(splitPiece[i]));
        }
        return board;
    }
}
