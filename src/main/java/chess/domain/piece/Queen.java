package chess.domain.piece;

import chess.domain.moveStrategy.QueenMove;

public class Queen extends Division {
    public static final int QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(color, "q", new QueenMove(color), new QueenMove(color));
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
        return QUEEN_SCORE;
    }
}
