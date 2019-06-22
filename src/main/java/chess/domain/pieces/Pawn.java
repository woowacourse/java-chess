package chess.domain.pieces;

import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    private boolean isFirstTurn;

    public Pawn(Color color) {
        super(color);
        isFirstTurn = true;
    }

    @Override
    public List<Point> move(Point source, Point target) {
        Direction currentDirection = calculateDirection(source, target);
        return calculatePath(source, target, currentDirection);
    }

    @Override
    public List<Point> attack(Point source, Point target) {
        Direction currentDirection = calculateAttackDirection(source, target);
        return calculateAttackPath(source, target, currentDirection);
    }

    private List<Point> calculatePath(Point source, Point target, Direction direction) {
        List<Point> path = this.makePathIncludedTarget(source, target, direction);

        if ((isFirstTurn && path.size() == TWO_STEP) || (path.size() == ONE_STEP)) {
            isFirstTurn = false;
            return path;
        }

        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    private Direction calculateDirection(Point source, Point target) {
        List<Direction> pawnDirections = Direction.pawnMoveDirection(color);
        Direction direction = Direction.of(source, target);

        if (!pawnDirections.contains(direction)) {
            throw new IllegalArgumentException("갈 수 없는 방향입니다.");
        }

        return direction;
    }

    private List<Point> calculateAttackPath(Point source, Point target, Direction direction) {
        List<Point> path = this.makePathIncludedTarget(source, target, direction);

        if (path.size() > ONE_STEP) {
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
