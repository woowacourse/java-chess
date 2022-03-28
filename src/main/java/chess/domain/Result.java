package chess.domain;

import chess.domain.piece.Color;

public enum Result {
    BLACK_WIN,
    WHITE_WIN,
    ;

    public static Result from(final Color color) {
        if (color == Color.BLACK) {
            return BLACK_WIN;
        }
        return WHITE_WIN;
    }
}
