package web;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.Map;

enum PieceType {
    PAWN, KING, QUEEN, ROOK, BISHOP, KNIGHT;

    static final Map<Class<? extends Piece>, PieceType> map = Map.of(
            Pawn.class, PAWN,
            King.class, KING,
            Queen.class, QUEEN,
            Rook.class, ROOK,
            Bishop.class, BISHOP,
            Knight.class, KNIGHT);

    public static PieceType valueOf(Piece piece) {
        return map.get(piece.getClass());
    }
}
