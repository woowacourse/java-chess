package chess.domain;

import chess.domain.movepatterns.QueenPattern;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Unit {

    public Queen(Integer color) {
        super(color, new QueenPattern());
    }

    @Override
    public boolean isValidMovePattern(Point source, Point target) {
        return movePattern.canMove(source, target);
    }

    @Override
    public List<Point> makePath(Point source, Point target) {
        List<Point> path = new ArrayList<>();
        Direction direction = Direction.of(source, target);

        Point nextPoint = source.plusPoint(direction.getDirection());
        while (!nextPoint.equals(target)) {
            path.add(nextPoint);
            nextPoint = nextPoint.plusPoint(direction.getDirection());
        }

        return path;
    }
}
