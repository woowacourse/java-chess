package chess.domain.pieces;

import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    public List<Point> move(Point source, Point target) {
        Direction currentDirection = calculateDirection(source, target);
        return calculatePath(source, target, currentDirection);
    }

    @Override
    public List<Point> attack(Point source, Point target) throws IllegalArgumentException {
        return move(source, target);
    }

    private List<Point> calculatePath(Point source, Point target, Direction direction) {
        List<Point> path = new ArrayList<>();
        Point nextPoint = source.plusPoint(direction);
        while (!nextPoint.equals(target)) {
            path.add(nextPoint);
            nextPoint = nextPoint.plusPoint(direction);
        }
        return path;
    }

    private Direction calculateDirection(Point source, Point target) throws IllegalArgumentException {
        List<Direction> queenDirections = Direction.everyDirection();
        Direction direction = Direction.of(source, target);

        if (!queenDirections.contains(direction)) {
            throw new IllegalArgumentException("갈 수 없는 방향입니다.");
        }

        return direction;
    }
}
