package chess.domain.piece;

import chess.domain.Color;

public final class Knight extends Piece {
    private static final double KNIGHT_SCORE = 2.5;

    public Knight(Color color) {
        super(color);
    }

    @Override
    public double getScore() {
        return KNIGHT_SCORE;
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
