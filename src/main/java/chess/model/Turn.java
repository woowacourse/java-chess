package chess.model;

import chess.model.piece.Piece;

public class Turn {

    private static final String ERROR_INVALID_PIECE = "[ERROR]: 빈 Piece 값이 전달되었습니다.";

    private PieceColor currentColor;
    private boolean isFinished = false;

    public Turn(PieceColor color) {
        this.currentColor = color;
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
