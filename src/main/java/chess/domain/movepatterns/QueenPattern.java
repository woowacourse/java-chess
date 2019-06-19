package chess.domain.movepatterns;

import chess.domain.Point;

public class QueenPattern implements MovePattern {

    private static final int VALID_INCLINE = 1;

    @Override
    public boolean canMove(Point source, Point target) {
        if (source.isEqualsX(target) || source.isEqualsY(target))
            return true;
        return Math.abs(source.calculateIncline(target)) == VALID_INCLINE;
    }
}
