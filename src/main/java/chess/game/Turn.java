package chess.game;

import chess.piece.Side;

public enum Turn {
    BLACK(Side.BLACK),
    WHITE(Side.WHITE);

    private final Side side;

    Turn(Side side) {
        this.side = side;
    }

    public boolean isCorrectTurn(Side side) {
        return this.side == side;
    }

    public Turn change() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
