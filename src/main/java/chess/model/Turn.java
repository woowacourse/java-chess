package chess.model;

import chess.model.piece.Piece;

public class Turn {

    private static final String ERROR_INVALID_PIECE = "[ERROR]: 빈 Piece 값이 전달되었습니다.";

    private PieceColor currentColor = PieceColor.WHITE;
    private boolean isFinished = false;

    boolean isTurnOf(Piece Piece) {
        return Piece.isSameColor(currentColor);
    }

    void nextState() {
        if (currentColor.isWhite()) {
            currentColor = PieceColor.BLACK;
            return;
        }
        currentColor = PieceColor.WHITE;
    }

    boolean isFinished() {
        return isFinished;
    }

    void finish() {
        isFinished = true;
        nextState();
    }

    public PieceColor getCurrentColor() {
        return currentColor;
    }
}
