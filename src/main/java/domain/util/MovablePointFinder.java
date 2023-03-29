package domain.util;

import domain.exception.PointOutOfBoardException;
import domain.piece.Piece;
import domain.point.Direction;
import domain.point.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovablePointFinder {
    private MovablePointFinder() {
    }

    public static List<Point> addPoints(Piece piece, Point from, Point to, List<List<Piece>> pieceStatus) {
        final List<Point> points = new ArrayList<>();
        Map<Direction, Integer> directionsAndRanges = piece.getMovableDirectionAndRange();

        directionsAndRanges.forEach((direction, range)
                -> addPointsThroughDirection(points, from, to, direction, range, pieceStatus));

        return points;
    }

    private static void addPointsThroughDirection(List<Point> points, Point fromPoint, Point toPoint, Direction direction, Integer range, List<List<Piece>> pieceStatus) {
        List<Point> pointsThroughDirection = new ArrayList<>();
        Point current = fromPoint;
        for (int count = 0; count < range; count++) {
            current = findPointThroughDirection(direction, current);

            if (current.equals(toPoint)) {
                Piece pieceOnTargetPoint = findPiece(pieceStatus, toPoint);
                if (!pieceOnTargetPoint.isEmpty() && !findPiece(pieceStatus, fromPoint).isOppositeWith(pieceOnTargetPoint)) {
                    rollback(points, pointsThroughDirection);
                    return;
                }
                pointsThroughDirection.add(current);
                break;
            }

            try {
                Piece piece = findPiece(pieceStatus, current);
                if (!piece.isEmpty()) {
                    rollback(points, pointsThroughDirection);
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }

            pointsThroughDirection.add(current);
        }
        points.addAll(pointsThroughDirection);
    }

    private static Point findPointThroughDirection(Direction direction, Point point) {
        try {
            point = point.move(direction);
        } catch (PointOutOfBoardException ignored) {}
        return point;
    }

    private static void rollback(List<Point> points, List<Point> pointsThroughDirection) {
        points.removeAll(pointsThroughDirection);
    }

    private static Piece findPiece(List<List<Piece>> pieceStatus, Point point) {
        return pieceStatus
                .get(point.findIndexFromBottom())
                .get(point.findIndexFromLeft());
    }
}
