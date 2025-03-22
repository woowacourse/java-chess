package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

import java.util.HashMap;
import java.util.Map;

public class Board {

    public final Map<Position, Piece> pieces = new HashMap<>();

    public void addPiece(Position position, Piece piece) {
        pieces.put(position, piece);
    }

    public void setup() {
        pieces.put(new Position(Column.A, Row.SEVEN), new Pawn(Color.BLACK));
        pieces.put(new Position(Column.B, Row.SEVEN), new Pawn(Color.BLACK));
        pieces.put(new Position(Column.C, Row.SEVEN), new Pawn(Color.BLACK));
        pieces.put(new Position(Column.D, Row.SEVEN), new Pawn(Color.BLACK));
        pieces.put(new Position(Column.E, Row.SEVEN), new Pawn(Color.BLACK));
        pieces.put(new Position(Column.F, Row.SEVEN), new Pawn(Color.BLACK));
        pieces.put(new Position(Column.G, Row.SEVEN), new Pawn(Color.BLACK));
        pieces.put(new Position(Column.H, Row.SEVEN), new Pawn(Color.BLACK));
        pieces.put(new Position(Column.A, Row.TWO), new Pawn(Color.WHITE));
        pieces.put(new Position(Column.B, Row.TWO), new Pawn(Color.WHITE));
        pieces.put(new Position(Column.C, Row.TWO), new Pawn(Color.WHITE));
        pieces.put(new Position(Column.D, Row.TWO), new Pawn(Color.WHITE));
        pieces.put(new Position(Column.E, Row.TWO), new Pawn(Color.WHITE));
        pieces.put(new Position(Column.F, Row.TWO), new Pawn(Color.WHITE));
        pieces.put(new Position(Column.G, Row.TWO), new Pawn(Color.WHITE));
        pieces.put(new Position(Column.H, Row.TWO), new Pawn(Color.WHITE));
        pieces.put(new Position(Column.A, Row.EIGHT), new Rook(Color.BLACK));
        pieces.put(new Position(Column.B, Row.EIGHT), new Knight(Color.BLACK));
        pieces.put(new Position(Column.C, Row.EIGHT), new Bishop(Color.BLACK));
        pieces.put(new Position(Column.D, Row.EIGHT), new Queen(Color.BLACK));
        pieces.put(new Position(Column.E, Row.EIGHT), new King(Color.BLACK));
        pieces.put(new Position(Column.F, Row.EIGHT), new Bishop(Color.BLACK));
        pieces.put(new Position(Column.G, Row.EIGHT), new Knight(Color.BLACK));
        pieces.put(new Position(Column.H, Row.EIGHT), new Rook(Color.BLACK));
        pieces.put(new Position(Column.A, Row.ONE), new Rook(Color.WHITE));
        pieces.put(new Position(Column.B, Row.ONE), new Knight(Color.WHITE));
        pieces.put(new Position(Column.C, Row.ONE), new Bishop(Color.WHITE));
        pieces.put(new Position(Column.D, Row.ONE), new Queen(Color.WHITE));
        pieces.put(new Position(Column.E, Row.ONE), new King(Color.WHITE));
        pieces.put(new Position(Column.F, Row.ONE), new Bishop(Color.WHITE));
        pieces.put(new Position(Column.G, Row.ONE), new Knight(Color.WHITE));
        pieces.put(new Position(Column.H, Row.ONE), new Rook(Color.WHITE));
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    public boolean containsKing() {
        for (Piece value : pieces.values()) {
            if (value instanceof King) {
                return true;
            }
        }
        return false;
    }
}
