package chess.domain.piece;

import chess.domain.moveStrategy.KnightMove;

public class Knight extends Division {
    public static final double KNIGHT_SCORE = 2.5;

    public Knight(Color color) {
        super(color, "n", new KnightMove(color), new KnightMove(color));
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
    }
}
