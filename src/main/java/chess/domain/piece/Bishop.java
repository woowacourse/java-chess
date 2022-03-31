package chess.domain.piece;

import chess.domain.Color;

public final class Bishop extends Piece {
    private static final int BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public double getScore() {
        return BISHOP_SCORE;
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
