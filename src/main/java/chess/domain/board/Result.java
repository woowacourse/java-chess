package chess.domain.board;

import chess.domain.piece.notation.Color;

public enum Result {
    BLACK_WIN,
    WHITE_WIN,
    EMPTY,
    ;

    public static Result from(final Color color) {
        if (color == Color.BLACK) {
            return BLACK_WIN;
        }
        if (color == Color.WHITE) {
            return WHITE_WIN;
        }
        return EMPTY;
    }
}
