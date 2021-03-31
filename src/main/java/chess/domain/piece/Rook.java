package chess.domain.piece;

import chess.domain.moveStrategy.RookMove;

public class Rook extends Division {
    public static final int ROOK_SCORE = 5;

    public Rook(Color color) {
        super(color, "r", new RookMove(color), new RookMove(color));
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
        return ROOK_SCORE;
    }
}
