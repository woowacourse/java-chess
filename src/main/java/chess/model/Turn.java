package chess.model;

import chess.model.piece.Piece;

public class Turn {

    private PieceColor currentColor = PieceColor.WHITE;
    private boolean isFinished = false;

    boolean isTurnOf(Piece Piece) {
        return isSameColor(Piece);
    }

    void nextState() {
        if (currentColor.isWhite()) {
            currentColor = PieceColor.BLACK;
            return;
        }
        currentColor = PieceColor.WHITE;
    }

    private boolean isSameColor(Piece sourcePiece) {
        return sourcePiece.isSameColor(currentColor);
    }

    boolean isFinished() {
        return isFinished;
    }

    void finish() {
        isFinished = true;
        nextState();
    }
}
