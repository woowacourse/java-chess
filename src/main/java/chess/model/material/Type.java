package chess.model.material;

import chess.model.piece.Piece;
import java.util.Arrays;
import java.util.function.Predicate;

public enum Type {
    PAWN(Piece::isPawn),
    ROOK(Piece::isRook),
    KNIGHT(Piece::isKnight),
    BISHOP(Piece::isBishop),
    QUEEN(Piece::isQueen),
    KING(Piece::isKing),
    NONE(Piece::isNone);

    private final Predicate<Piece> condition;

    Type(Predicate<Piece> condition) {
        this.condition = condition;
    }

    public static Type findType(Piece piece) {
        return Arrays.stream(values())
            .filter(type -> type.condition.test(piece))
            .findFirst()
            .orElse(NONE);
    }
}
