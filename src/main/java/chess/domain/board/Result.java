package chess.domain.board;

import chess.domain.piece.Color;

public enum Result {
    BLACK_WIN,
    WHITE_WIN,
    ;

    public static Result from(final Board board) {
        if (board.hasKing(Color.BLACK)) {
            return BLACK_WIN;
        }
        return WHITE_WIN;
    }
}
