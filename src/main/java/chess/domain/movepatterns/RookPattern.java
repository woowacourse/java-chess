package chess.domain.movepatterns;

import chess.domain.Point;

public class RookPattern implements MovePattern {

    @Override
    public boolean canMove(Point source, Point target) {
        return source.isEqualsX(target) || source.isEqualsY(target);
    }
}
