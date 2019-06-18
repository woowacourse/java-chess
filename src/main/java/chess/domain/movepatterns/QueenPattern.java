package chess.domain.movepatterns;

import chess.domain.Point;

public class QueenPattern implements MovePattern {

    @Override
    public boolean canMove(Point source, Point target) {
        if (source.isEqualsX(target) || source.isEqualsY(target))
            return true;
        return source.calculateIncline(target) == 1;
    }
}
