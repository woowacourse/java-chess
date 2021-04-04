package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class PiecesFactory {
    private static final String KING = "k";
    private static final String QUEEN = "q";
    private static final String BISHOP = "b";
    private static final String KNIGHT = "n";
    private static final String ROOK = "r";
    private static final String PAWN = "p";
    private static final int PAWN_SIZE = 8;

    public static Pieces whitePieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(Rook.from(ROOK, Position.valueOf("1", "a")));
        pieces.add(Knight.from(KNIGHT, Position.valueOf("1", "b")));
        pieces.add(Bishop.from(BISHOP, Position.valueOf("1", "c")));
        pieces.add(Queen.from(QUEEN, Position.valueOf("1", "d")));
        pieces.add(King.from(KING, Position.valueOf("1", "e")));
        pieces.add(Bishop.from(BISHOP, Position.valueOf("1", "f")));
        pieces.add(Knight.from(KNIGHT, Position.valueOf("1", "g")));
        pieces.add(Rook.from(ROOK, Position.valueOf("1", "h")));

        for (int idx = 0; idx < PAWN_SIZE; idx++) {
            pieces.add(Pawn.from(PAWN, Position.valueOf("2", Character.toString((char) ('a' + idx)))));
        }

        return new Pieces(pieces);
    }

    public static Pieces blackPieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(Rook.from(ROOK.toUpperCase(), Position.valueOf("8", "a")));
        pieces.add(Knight.from(KNIGHT.toUpperCase(), Position.valueOf("8", "b")));
        pieces.add(Bishop.from(BISHOP.toUpperCase(), Position.valueOf("8", "c")));
        pieces.add(Queen.from(QUEEN.toUpperCase(), Position.valueOf("8", "d")));
        pieces.add(King.from(KING.toUpperCase(), Position.valueOf("8", "e")));
        pieces.add(Bishop.from(BISHOP.toUpperCase(), Position.valueOf("8", "f")));
        pieces.add(Knight.from(KNIGHT.toUpperCase(), Position.valueOf("8", "g")));
        pieces.add(Rook.from(ROOK.toUpperCase(), Position.valueOf("8", "h")));

        for (int idx = 0; idx < PAWN_SIZE; idx++) {
            pieces.add(Pawn.from(PAWN.toUpperCase(), Position.valueOf("7", Character.toString((char) ('a' + idx)))));
        }

        return new Pieces(pieces);
    }
}
