package chess.domain.movepatterns;

import chess.domain.Point;

public interface MovePattern {

    boolean canMove(Point source, Point target);
}
