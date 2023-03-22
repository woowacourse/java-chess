package chess.domain.command;

import chess.domain.piece.Side;

public enum Turn {
    BLACK(Side.BLACK, "black"),
    WHITE(Side.WHITE, "white");

    private final Side side;
    private final String displayName;

    Turn(final Side side, final String displayName) {
        this.side = side;
        this.displayName = displayName;
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

    public String getDisplayName() {
        return displayName;
    }
}
