package chess.domain.movepatterns;

import chess.domain.Point;

public class BishopPattern implements MovePattern {

    private static final int VALID_INCLIE = 1;

    @Override
    public boolean canMove(Point source, Point target) {
        return source.calculateIncline(target) == VALID_INCLIE;
    }
}
