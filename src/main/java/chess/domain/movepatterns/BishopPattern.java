package chess.domain.movepatterns;

import chess.domain.Point;
import chess.domain.pieces.Direction;

import java.util.List;

public class BishopPattern implements MovePattern {

    @Override
    public boolean canMove(Point source, Point target) {
        List<Direction> bishopDirections = Direction.diagonalDirection();
        return bishopDirections.contains(Direction.of(source, target));
    }
}
