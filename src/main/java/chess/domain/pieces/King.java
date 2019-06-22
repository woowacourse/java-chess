package chess.domain.pieces;

import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final int EMPTY_SIZE = 0;

    public King(Color color) {
        super(color);
    }

    @Override
    public List<Point> move(Point source, Point target) {
        Direction currentDirection = calculateDirection(source, target);
        return calculatePath(source, target, currentDirection);
    }

    @Override
    public List<Point> attack(Point source, Point target) {
        return move(source, target);
    }

    private List<Point> calculatePath(Point source, Point target, Direction direction) {
        List<Point> path = new ArrayList<>();
        Point nextPoint = source.plusPoint(direction);
        while (!nextPoint.equals(target)) {
            path.add(nextPoint);
            nextPoint = nextPoint.plusPoint(direction);
        }

        if (path.size() > EMPTY_SIZE) {
            throw new IllegalArgumentException("갈 수 없는 위치입니다.");
        }

        return path;
    }

    private Direction calculateDirection(Point source, Point target) {
        List<Direction> kingDirections = Direction.everyDirection();
        Direction direction = Direction.of(source, target);

        if (!kingDirections.contains(direction)) {
            throw new IllegalArgumentException("갈 수 없는 방향입니다.");
        }

        return direction;
    }
}
