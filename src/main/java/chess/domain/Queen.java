package chess.domain;

import chess.domain.movepatterns.KingPattern;
import chess.domain.movepatterns.MovePattern;

import java.util.ArrayList;
import java.util.List;

public class Queen {
    private final MovePattern movePattern = new KingPattern();
    private final Integer color;

    public Queen(Integer color) {
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
