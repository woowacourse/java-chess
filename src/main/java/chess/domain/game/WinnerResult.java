package chess.domain.game;

import chess.domain.pieces.piece.Color;

public enum WinnerResult {
    WHITE,
    BLACK,
    TIE,
    ;

    public static WinnerResult from(final Color color) {
        if (color.equals(Color.WHITE)) {
            return WHITE;
        }
        if (color.equals(Color.BLACK)) {
            return BLACK;
        }
        return TIE;
    }
}
