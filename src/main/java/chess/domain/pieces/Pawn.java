package chess.domain.pieces;

import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int EMPTY = 0;
    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    @Override
    public List<Point> action(Point source, Point target, boolean hasEnemy) {
        Direction currentDirection;
        if (hasEnemy) {
            currentDirection = calculateAttackDirection(source, target);
            return calculateAttackPath(source, target, currentDirection);
        }
        currentDirection = calculateMoveDirection(source, target);
        return calculateMovePath(source, target, currentDirection);
    }

    private List<Point> calculateMovePath(Point source, Point target, Direction direction) {
        List<Point> path = this.makePathIncludedTarget(source, target, direction);

        if (getColor().equals(Color.WHITE) && source.getY() == 2 && path.size() == TWO_STEP) {
            return path;
        }
        if (getColor().equals(Color.BLACK) && source.getY() == 7 && path.size() == TWO_STEP) {
            return path;
        }
        if ((path.size() == ONE_STEP)) {
            return path;
        }

        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    private Direction calculateMoveDirection(Point source, Point target) {
        List<Direction> pawnDirections = Direction.pawnMoveDirection(color);
        Direction direction = Direction.of(source, target);

        if (!pawnDirections.contains(direction)) {
            throw new IllegalArgumentException("갈 수 없는 방향입니다.");
        }

        return direction;
    }

    private List<Point> calculateAttackPath(Point source, Point target, Direction direction) {
        List<Point> path = makePath(source, target, direction);

        if (path.size() > EMPTY) {
            throw new IllegalArgumentException("갈 수 없는 위치입니다.");
        }

        return path;
    }

    private Direction calculateAttackDirection(Point source, Point target) {
        List<Direction> pawnDirections = Direction.pawnAttackDirection(color);
        Direction direction = Direction.of(source, target);

        if (!pawnDirections.contains(direction)) {
            throw new IllegalArgumentException("갈 수 없는 방향입니다.");
        }

        return direction;
    }

    private List<Point> makePathIncludedTarget(Point source, Point target, Direction direction) {
        List<Point> path = new ArrayList<>();
        Point nextPoint = source.plusPoint(direction);

        while (!nextPoint.equals(target)) {
            path.add(nextPoint);
            nextPoint = nextPoint.plusPoint(direction);
        }
        path.add(nextPoint);
        return path;
    }
}
