package chess.domain.pieces;

import chess.domain.Point;
import chess.domain.movepatterns.BishopPattern;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, new BishopPattern());
    }

    @Override
    public boolean isValidMovePattern(Point source, Point target) {
        return movePattern.canMove(source, target);
    }

    @Override
    public List<Point> makePath(Point source, Point target) {
        List<Point> path = new ArrayList<>();
        Direction direction = Direction.of(source, target);

        Point nextPoint = source.plusPoint(direction);
        while (!nextPoint.equals(target)) {
            path.add(nextPoint);
            nextPoint = nextPoint.plusPoint(direction);
        }

        return path;
    }
}
