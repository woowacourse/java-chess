package chess.domain.movepatterns;

import chess.domain.Point;
import chess.domain.pieces.Direction;

import java.util.List;

public class KnightPattern implements MovePattern {

    @Override
    public boolean canMove(Point source, Point target) {
        List<Direction> knightDirections = Direction.knightDirection();
        return knightDirections.stream()
                .anyMatch(direction -> source.plusPoint(direction).equals(target));
    }
}
