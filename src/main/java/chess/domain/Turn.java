package chess.domain;

import chess.domain.piece.Piece;

public class Turn {
    private final int count;

    public Turn() {
        this.count = 0;
    }

    private Turn(int count) {
        this.count = count;
    }

    public Turn nextTurn() {
        return new Turn(count + 1);
    }

    public boolean isMoveOrder(Piece piece) {
        if (piece.isWhite()) {
            return isWhiteTurn();
        }

        return isBlackTurn();
    }

    private boolean isWhiteTurn() {
        return count % 2 == 0;
    }

    private boolean isBlackTurn() {
        return count % 2 == 1;
    }
}
