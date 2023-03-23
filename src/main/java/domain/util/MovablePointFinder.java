package domain.util;

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
        List<Point> movablePoints = new ArrayList<>();
        Map<Direction, Integer> movableRange = piece.getMovableRange();

        onCaseOfDirection(fromPoint, movablePoints, movableRange.get(Direction.UP));
        movablePoints = onCaseOfPointOutOfBoard(pieceStatus, movablePoints);

        onCaseOfPieceBetWeenPath(pieceStatus, movablePoints, toPoint, piece);

        return movablePoints;
    }

    private static List<Point> onCaseOfPointOutOfBoard(List<List<Piece>> pieceStatus, List<Point> movablePoints) {
        List<Point> newMovablePoints = new ArrayList<>(movablePoints);
        for (Point movablePoint : movablePoints) {
            checkPointOutOfBoard(pieceStatus, newMovablePoints, movablePoint);
        }
        return newMovablePoints;
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

    private static void onCaseOfDirection(Point point, List<Point> movablePoints, Integer movableCount) {
        if (movableCount != null) {
            addMovablePoints(point, movablePoints, movableCount);
        }
    }

    private static void addMovablePoints(Point point, List<Point> movablePoints, Integer movableCount) {
        Point movablePoint = point;
        for (int i = 0; i < movableCount; i++) {
            movablePoint = movablePoint.up();
            movablePoints.add(movablePoint);
        }
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
                throw new IllegalArgumentException("이동하려는 길 사이에 다른 장기말이 위치하고 있습니다.");
            }
        }
    }

    private static boolean isToPointReachable(Point toPoint, Piece piece, Point movablePoint, Piece pieceOnMovablePoint) {
        return movablePoint.equals(toPoint) && piece.isOppositeWith(pieceOnMovablePoint);
    }
}
