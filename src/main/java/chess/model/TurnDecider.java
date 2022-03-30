package chess.model;

import chess.model.piece.Piece;

public class TurnDecider {

    private PieceColor currentColor = PieceColor.WHITE;

    public boolean isTurnOf(Piece Piece) {
        return isSameColor(Piece);
    }

    public void nextState() {
        if (currentColor.isWhite()) {
            currentColor = PieceColor.BLACK;
            return;
        }
        currentColor = PieceColor.WHITE;
    }

    private boolean isSameColor(Piece sourcePiece) {
        return sourcePiece.isSameColor(currentColor);
    }
}
