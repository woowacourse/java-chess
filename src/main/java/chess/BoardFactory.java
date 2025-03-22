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

public class BoardFactory {

    public static Board makeBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        Piece pawn = new Piece(null, new Pawn(Team.WHITE), null);
        Piece rook = new Piece(null, new Rook(Team.WHITE), null);
        Piece bishop = new Piece(new Bishop(Team.WHITE), null, null);
        Piece queen = new Piece(new Queen(Team.WHITE), new Queen(Team.WHITE), null);
        Piece king = new Piece(new King(Team.WHITE), new Queen(Team.WHITE), null);
        Piece knight = new Piece(null, null, new Knight(Team.WHITE));

        pieces.put(new Position(Column.A, Row.TWO), pawn);
        pieces.put(new Position(Column.B, Row.TWO), pawn);
        pieces.put(new Position(Column.C, Row.TWO), pawn);
        pieces.put(new Position(Column.D, Row.TWO), pawn);
        pieces.put(new Position(Column.E, Row.TWO), pawn);
        pieces.put(new Position(Column.F, Row.TWO), pawn);
        pieces.put(new Position(Column.G, Row.TWO), pawn);
        pieces.put(new Position(Column.H, Row.TWO), pawn);
        pieces.put(new Position(Column.A, Row.ONE), rook);
        pieces.put(new Position(Column.B, Row.ONE), knight);
        pieces.put(new Position(Column.C, Row.ONE), bishop);
        pieces.put(new Position(Column.D, Row.ONE), queen);
        pieces.put(new Position(Column.E, Row.ONE), king);
        pieces.put(new Position(Column.F, Row.ONE), bishop);
        pieces.put(new Position(Column.G, Row.ONE), knight);
        pieces.put(new Position(Column.H, Row.ONE), rook);

        pieces.put(new Position(Column.A, Row.SEVEN), pawn);
        pieces.put(new Position(Column.B, Row.SEVEN), pawn);
        pieces.put(new Position(Column.C, Row.SEVEN), pawn);
        pieces.put(new Position(Column.D, Row.SEVEN), pawn);
        pieces.put(new Position(Column.E, Row.SEVEN), pawn);
        pieces.put(new Position(Column.F, Row.SEVEN), pawn);
        pieces.put(new Position(Column.G, Row.SEVEN), pawn);
        pieces.put(new Position(Column.H, Row.SEVEN), pawn);
        pieces.put(new Position(Column.A, Row.EIGHT), rook);
        pieces.put(new Position(Column.B, Row.EIGHT), knight);
        pieces.put(new Position(Column.C, Row.EIGHT), bishop);
        pieces.put(new Position(Column.D, Row.EIGHT), queen);
        pieces.put(new Position(Column.E, Row.EIGHT), king);
        pieces.put(new Position(Column.F, Row.EIGHT), bishop);
        pieces.put(new Position(Column.G, Row.EIGHT), knight);
        pieces.put(new Position(Column.H, Row.EIGHT), rook);

        return new Board(pieces);
    }
}
