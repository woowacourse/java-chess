package chess.model;

import chess.model.piece.Piece;

public class Turn {

    private static final String ERROR_INVALID_COLOR_VALUE = "[ERROR] 올바르지 않은 색 입력입니다.";

    private PieceColor currentColor;
    private boolean isFinished = false;

    public Turn(PieceColor color) {
        this.currentColor = color;
    }

    public Turn(String colorValue) {
        try {
            this.currentColor = PieceColor.valueOf(colorValue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ERROR_INVALID_COLOR_VALUE);
        }
    }

    public Turn() {
        this(PieceColor.WHITE);
    }

    boolean isTurnOf(Piece Piece) {
        return Piece.isSameColor(currentColor);
    }

    public void nextTurn() {
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
        nextTurn();
    }

    public PieceColor getCurrentColor() {
        return currentColor;
    }
}
