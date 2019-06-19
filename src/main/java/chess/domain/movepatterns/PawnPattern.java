package chess.domain.movepatterns;

import chess.domain.Point;

public class PawnPattern implements MovePattern {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;

    @Override
    public boolean canMove(Point source, Point target) {
        return (source.minusX(target) == ZERO) &&
                (Math.abs(source.minusY(target)) == ONE || Math.abs(source.minusY(target)) == TWO);
    }
}
