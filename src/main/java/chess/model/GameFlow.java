package chess.model;

import chess.model.piece.ChessPieceColor;

public enum GameFlow {
    CONTINUE, BLACK_WIN, WHITE_WIN, DRAW;

    public static GameFlow valueOf(final double blackScore, final double whiteScore) {
        if (blackScore > whiteScore) {
            return BLACK_WIN;
        }
        if (blackScore < whiteScore) {
            return WHITE_WIN;
        }
        return DRAW;
    }

    public static GameFlow valueOf(final ChessPieceColor thisTurnColor) {
        if (thisTurnColor == ChessPieceColor.BLACK) {
            return BLACK_WIN;
        }
        return WHITE_WIN;
    }
}
