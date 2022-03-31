package chess.domain.piece;

import chess.domain.Color;

public final class King extends Piece {
    private static final int NO_SCORE = 0;

    public King(Color color) {
        super(color);
    }

    @Override
    public double getScore() {
        return NO_SCORE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
