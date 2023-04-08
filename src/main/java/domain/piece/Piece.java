package domain.piece;

import dao.Movement;
import domain.Turn;
import domain.point.Direction;
import domain.point.Point;
import util.ExceptionMessages;

import java.util.*;

public abstract class Piece {
    protected final Turn turn;

    public Piece(Turn turn) {
        this.turn = turn;
    }

    public final boolean isTurnOf(Turn turn) {
        return this.turn == turn;
    }

    public final boolean isOppositeWith(Piece piece) {
        return this.turn != piece.turn;
    }

    public boolean canMove(Movement movement, Map<Point, Piece> status, Turn turn) {
        if (this.turn != turn) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GAME_TURN);
        }

        List<Point> points = new ArrayList<>();

        Map<Direction, Integer> directionAndRanges = getMovableDirectionAndRange();
        directionAndRanges.forEach((direction, range)
                -> points.addAll(findPointsThroughDirection(movement, direction, range, status)));
        points.addAll(findSpecializedPoints(movement, status));

        return points.contains(movement.getDestinationPoint());
    }

    private List<Point> findPointsThroughDirection(Movement movement, Direction direction, Integer range, Map<Point, Piece> status) {
        List<Point> points = new ArrayList<>();
        Point currentPoint = movement.getStartingPoint();
        for (int count = 0; count < range; count++) {
            Optional<Point> point = findPointThroughDirection(currentPoint, movement, direction, status);
            if (point.isPresent()) {
                points.add(point.get());
                currentPoint = point.get();
            }
        }
        return points;
    }

    private Optional<Point> findPointThroughDirection(Point previousPoint, Movement movement, Direction direction, Map<Point, Piece> pieceStatus) {
        Point point = movePointThroughDirection(previousPoint, direction);
        // 한 칸 앞으로

        if (point.equals(movement.getDestinationPoint())) {
            if (isDestinationReachable(
                    pieceStatus.get(movement.getStartingPoint()),
                    pieceStatus.get(movement.getDestinationPoint())
            )) {
                return Optional.of(point);
            }
        }
        // 목적지와 같은 칸

        Piece piece = pieceStatus.get(point);
        if (!piece.exists()) {
            return Optional.empty();
        }
        // 목적지로 가는 도중의 길

        return Optional.of(point);
    }

    private Point movePointThroughDirection(Point point, Direction direction) {
        try {
            point = point.move(direction);
        } catch (IllegalArgumentException ignored) {}
        return point;
    }

    private boolean isDestinationReachable(Piece piece, Piece pieceOnDestination) {
        return pieceOnDestination.exists() || piece.isOppositeWith(pieceOnDestination);
    }

    protected abstract List<Point> findSpecializedPoints(Movement movement, Map<Point, Piece> status);

    public final String getSymbol() {
        if (this.turn == Turn.BLACK) {
            return getInitial().toUpperCase();
        }
        return getInitial();
    }

    protected abstract String getInitial();

    public abstract Map<Direction, Integer> getMovableDirectionAndRange();

    public abstract float getScore(List<Piece> line);

    public abstract boolean exists();
}
