package chess.domain.movepatterns;

import chess.domain.Point;
import chess.domain.pieces.Direction;

import java.util.List;

public class KingPattern implements MovePattern {

    @Override
    public boolean canMove(Point source, Point target) {
        List<Direction> kingDirections = Direction.everyDirection();
        return kingDirections.contains(Direction.of(source, target));
    }
}
