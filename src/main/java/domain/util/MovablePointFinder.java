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

    public static List<Point> addPoints(Point from, Point to, List<List<Piece>> pieceStatus, Piece piece) {
        final List<Point> points = new ArrayList<>();
        Map<Direction, Integer> directionsAndRanges = piece.getMovableDirectionAndRange();

        directionsAndRanges.forEach((direction, range)
                -> addPoints(from, points, direction, range));

        removeInvalidPoints(to, pieceStatus, piece, points);

        return points;
    }

    private static void removeInvalidPoints(Point to, List<List<Piece>> pieceStatus, Piece piece, List<Point> points) {
        removePointOutOfBoard(pieceStatus, points);
        removePointsAlreadyAssigned(pieceStatus, points, to, piece);
    }

    private static void addPoints(Point point, List<Point> points, Direction direction, Integer range) {
        if (range != null) {
            addPointsThroughDirection(point, points, direction, range);
        }
    }

    private static void addPointsThroughDirection(Point point, List<Point> points, Direction direction, Integer range) {
        Point movablePoint = point;
        for (int count = 0; count < range; count++) {
            movablePoint = addPointThroughDirection(points, direction, movablePoint);
        }
    }

    private static Point addPointThroughDirection(List<Point> movablePoints, Direction direction, Point movablePoint) {
        try {
            movablePoint = movablePoint.move(direction);
            movablePoints.add(movablePoint);
        } catch (PointOutOfBoardException ignored) {}
        return movablePoint;
    }

    private static void removePointOutOfBoard(List<List<Piece>> pieceStatus, List<Point> movablePoints) {
        List<Point> tempPoints = new ArrayList<>(movablePoints);
        for (Point movablePoint : movablePoints) {
            checkAndRemovePointOutOfBoard(pieceStatus, tempPoints, movablePoint);
        }
        movablePoints.clear();
        movablePoints.addAll(tempPoints);
    }

    private static void checkAndRemovePointOutOfBoard(List<List<Piece>> pieceStatus, List<Point> newMovablePoints, Point movablePoint) {
        try {
            findPiece(pieceStatus, movablePoint);
        } catch (IndexOutOfBoundsException e) {
            newMovablePoints.remove(movablePoint);
        }
    }

    private static void removePointsAlreadyAssigned(List<List<Piece>> pieceStatus, List<Point> points, Point point, Piece piece) {
        List<Point> copyOfMovablePoints = new ArrayList<>(points);
        for (Point targetPoint : copyOfMovablePoints) {
            Piece targetPiece = findPiece(pieceStatus, targetPoint);
            removePointAlreadyAssigned(points, point, piece, targetPoint, targetPiece);
        }
    }

    private static Piece findPiece(List<List<Piece>> pieceStatus, Point point) {
        return pieceStatus
                .get(point.findIndexFromBottom())
                .get(point.findIndexFromLeft());
    }

    private static void removePointAlreadyAssigned(List<Point> points, Point point, Piece piece, Point targetPoint, Piece targetPiece) {
        if (targetPoint.equals(point) && piece.isOppositeWith(targetPiece)) {
            return;
        }
        if (!targetPiece.isEmpty()) {
            points.remove(targetPoint);
        }
    }
}
