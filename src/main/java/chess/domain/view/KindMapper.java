package chess.domain.view;

import chess.domain.piece.Color;
import chess.domain.piece.Kind;

public enum KindMapper {
    BLACK_PAWN(Kind.PAWN, Color.BLACK, "P"),
    WHITE_PAWN(Kind.PAWN, Color.WHITE, "p"),
    BLACK_KING(Kind.KING, Color.BLACK, "K"),
    WHITE_KING(Kind.KING, Color.WHITE, "k"),
    BLACK_QUEEN(Kind.QUEEN, Color.BLACK, "Q"),
    WHITE_QUEEN(Kind.QUEEN, Color.WHITE, "q"),
    BLACK_BISHOP(Kind.BISHOP, Color.BLACK, "B"),
    WHITE_BISHOP(Kind.BISHOP, Color.WHITE, "b"),
    BLACK_ROOK(Kind.ROOK, Color.BLACK, "R"),
    WHITE_ROOK(Kind.ROOK, Color.WHITE, "r"),
    BLACK_KNIGHT(Kind.KNIGHT, Color.BLACK, "N"),
    WHITE_KNIGHT(Kind.KNIGHT, Color.WHITE, "n"),
    EMPTY(Kind.EMPTY, Color.NONE, ".");

    private final Kind kind;
    private final Color color;
    private final String name;

    KindMapper(final Kind kind, final Color color, final String name) {
        this.kind = kind;
        this.color = color;
        this.name = name;
    }
}
