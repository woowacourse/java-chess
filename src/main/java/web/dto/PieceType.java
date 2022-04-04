package web.dto;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.Map;

public enum PieceType {
    PAWN, KING, QUEEN, ROOK, BISHOP, KNIGHT;

    static final Map<Class<? extends Piece>, PieceType> map = Map.of(
            Pawn.class, PAWN,
            King.class, KING,
            Queen.class, QUEEN,
            Rook.class, ROOK,
            Bishop.class, BISHOP,
            Knight.class, KNIGHT);

    public Piece createPiece(Color color) {
        if (this == PAWN) {
            return new Pawn(color);
        }
        if (this == KING) {
            return new King(color);
        }
        if (this == QUEEN) {
            return new Queen(color);
        }
        if (this == ROOK) {
            return new Rook(color);
        }
        if (this == BISHOP) {
            return new Bishop(color);
        }
        return new Knight(color);
    }

    public static PieceType valueOf(Piece piece) {
        return map.get(piece.getClass());
    }
}
