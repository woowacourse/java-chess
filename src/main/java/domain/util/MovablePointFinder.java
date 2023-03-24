package domain.util;

import domain.exception.BlockedPathException;
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

    public static List<Point> findMovablePoints(Point fromPoint, Point toPoint, List<List<Piece>> pieceStatus, Piece piece) {
        final List<Point> movablePoints = new ArrayList<>();
        Map<Direction, Integer> movableRange = piece.getMovableRange();

        movableRange.forEach((direction, integer) -> onCaseOfDirection(fromPoint, movablePoints, integer, direction));
        onCaseOfPointOutOfBoard(pieceStatus, movablePoints);

        onCaseOfPieceBetWeenPath(pieceStatus, movablePoints, toPoint, piece);

        return movablePoints;
    }

    private static void onCaseOfPointOutOfBoard(List<List<Piece>> pieceStatus, List<Point> movablePoints) {
        List<Point> newMovablePoints = new ArrayList<>(movablePoints);
        for (Point movablePoint : movablePoints) {
            checkPointOutOfBoard(pieceStatus, newMovablePoints, movablePoint);
        }
        movablePoints.clear();
        movablePoints.addAll(newMovablePoints);
    }

    private static void checkPointOutOfBoard(List<List<Piece>> pieceStatus, List<Point> newMovablePoints, Point movablePoint) {
        try {
            pieceStatus
                    .get(movablePoint.findIndexFromBottom())
                    .get(movablePoint.findIndexFromLeft());
        } catch (IndexOutOfBoundsException e) {
            newMovablePoints.remove(movablePoint);
        }
    }

    private static void onCaseOfDirection(Point point, List<Point> movablePoints, Integer movableCount, Direction direction) {
        if (movableCount != null) {
            addMovablePoints(point, movablePoints, movableCount, direction);
        }
    }

    private static void addMovablePoints(Point point, List<Point> movablePoints, Integer movableCount, Direction direction) {
        Point movablePoint = point;
        for (int count = 0; count < movableCount; count++) {
            movablePoint = addMovablePoint(movablePoints, direction, movablePoint);
        }
    }

    private static Point addMovablePoint(List<Point> movablePoints, Direction direction, Point movablePoint) {
        try {
            movablePoint = movablePoint.move(direction);
            movablePoints.add(movablePoint);
        } catch (PointOutOfBoardException ignored) {}
        return movablePoint;
    }

    private static void onCaseOfPieceBetWeenPath(List<List<Piece>> pieceStatus, List<Point> movablePoints, Point toPoint, Piece piece) {
        for (Point movablePoint : movablePoints) {
            Piece pieceOnMovablePoint = pieceStatus
                    .get(movablePoint.findIndexFromBottom())
                    .get(movablePoint.findIndexFromLeft());

            if (isToPointReachable(toPoint, piece, movablePoint, pieceOnMovablePoint)) {
                continue;
            }

            if (!pieceOnMovablePoint.isEmpty()) {
                throw new BlockedPathException();
            }
        }
    }

    private static boolean isToPointReachable(Point toPoint, Piece piece, Point movablePoint, Piece pieceOnMovablePoint) {
        return movablePoint.equals(toPoint) && piece.isOppositeWith(pieceOnMovablePoint);
    }
}
