package chess.domain.piece;

import chess.domain.moveStrategy.BishopMove;

public class Bishop extends Division {
    public static final int BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(color, "b", new BishopMove(color), new BishopMove(color));
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
        return BISHOP_SCORE;
    }
}
