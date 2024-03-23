package chess.model.material;

import chess.model.piece.Piece;
import java.util.Arrays;

public enum Type {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING,
    NONE;

    public static Type findType(Piece piece) {
        return Arrays.stream(values())
            .filter(piece::isSameType)
            .findFirst()
            .orElse(NONE);
    }

    public static Type findType(String pieceName) {
        return Arrays.stream(values())
            .filter(type -> type.name().equals(pieceName))
            .findFirst()
            .orElse(NONE);
    }

    public boolean isNotNone() {
        return !isNone();
    }

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isPawn() {
        return this == PAWN;
    }

    public boolean isKnight() {
        return this == KNIGHT;
    }
}
