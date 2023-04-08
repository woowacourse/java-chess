package domain.piece;

import dao.Movement;
import domain.Turn;
import domain.point.Direction;
import domain.point.Point;

import java.util.*;

public class Pawn extends Piece {

    public static final float SCORE = 1f;
    private boolean hasMoved = false;

    public Pawn(Turn turn) {
        super(turn);
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public float getScore(List<Piece> line) {
        if (2 <= Collections.frequency(line, this)) {
            return SCORE / 2;
        }
        return SCORE;
    }

    @Override
    public Map<Direction, Integer> getMovableDirectionAndRange() {
        Map<Direction, Integer> movableRange = new HashMap<>();
        if (isTurnOf(Turn.BLACK)) {
            return getDirectionAndRange(movableRange, Direction.DOWN);
        }

        return getDirectionAndRange(movableRange, Direction.UP);
    }

    @Override
    protected List<Point> findSpecializedPoints(Movement movement, Map<Point, Piece> status) {
        List<Point> points = new ArrayList<>();
        if (turn == Turn.BLACK) {
            findSpecializedPointsForBlack(movement.getStartingPoint())
                    .forEach(point -> findDiagonalEnemyPoint(status, point, points));
        }
        if (turn == Turn.WHITE) {
            findSpecializedPointsForWhite(movement.getStartingPoint())
                    .forEach(point -> findDiagonalEnemyPoint(status, point, points));
        }
        return points;
    }

    private List<Point> findSpecializedPointsForWhite(Point point) {
        List<Point> points = new ArrayList<>();
        try {
            points.add(point.leftUp());
        } catch (IllegalArgumentException ignored) {}
        try {
            points.add(point.rightUp());
        } catch (IllegalArgumentException ignored) {}
        return points;
    }

    private List<Point> findSpecializedPointsForBlack(Point point) {
        List<Point> points = new ArrayList<>();
        try {
            points.add(point.leftDown());
        } catch (IllegalArgumentException ignored) {}
        try {
            points.add(point.rightDown());
        } catch (IllegalArgumentException ignored) {}
        return points;
    }

    private void findDiagonalEnemyPoint(Map<Point, Piece> status, Point point, List<Point> points) {
        Piece piece = status.get(point);
        if (!piece.exists() && piece.isOppositeWith(this)) {
            points.add(point);
        }
    }

    private Map<Direction, Integer> getDirectionAndRange(Map<Direction, Integer> movableRange, Direction direction) {
        if (hasMoved) {
            movableRange.put(direction, 1);
            return movableRange;
        }
        movableRange.put(direction, 2);
        hasMoved = true;
        return movableRange;
    }

    @Override
    protected String getInitial() {
        return "p";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pawn pawn = (Pawn) o;
        return hasMoved == pawn.hasMoved && turn == pawn.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasMoved);
    }
}
