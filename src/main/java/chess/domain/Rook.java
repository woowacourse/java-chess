package chess.domain;

import chess.domain.movepatterns.MovePattern;
import chess.domain.movepatterns.RookPattern;

import java.util.ArrayList;
import java.util.List;

public class Rook {
    private final MovePattern movePattern = new RookPattern();
    private final Integer color;

    public Rook(Integer color) {
        this.color = color;
    }

    public boolean isValidMovePattern(Point source, Point target) {
        return movePattern.canMove(source, target);
    }

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
