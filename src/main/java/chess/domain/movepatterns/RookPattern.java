package chess.domain.movepatterns;

import chess.domain.Point;
import chess.domain.pieces.Direction;

import java.util.List;

public class RookPattern implements MovePattern {

    @Override
    public boolean canMove(Point source, Point target) {
        List<Direction> rookDirections = Direction.linearDirection();
        return rookDirections.contains(Direction.of(source, target));
    }
}
