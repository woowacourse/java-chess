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
        List<Point> movablePoints = new ArrayList<>();
        Map<Direction, Integer> movableRange = piece.getMovableRange();

        onCaseOfDirection(fromPoint, movablePoints, movableRange.get(Direction.UP), Direction.UP);
        onCaseOfDirection(fromPoint, movablePoints, movableRange.get(Direction.DOWN), Direction.DOWN);
        onCaseOfDirection(fromPoint, movablePoints, movableRange.get(Direction.LEFT), Direction.LEFT);
        onCaseOfDirection(fromPoint, movablePoints, movableRange.get(Direction.RIGHT), Direction.RIGHT);
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

    private static void onCaseOfDirection(Point point, List<Point> movablePoints, Integer movableCount, Direction direction) {
        if (movableCount != null) {
            addMovablePoints(point, movablePoints, movableCount, direction);
        }
    }

    private static void addMovablePoints(Point point, List<Point> movablePoints, Integer movableCount, Direction direction) {
        Point movablePoint = point;
        for (int i = 0; i < movableCount; i++) {
            try {
                if (direction == Direction.UP) movablePoint = movablePoint.up();
                if (direction == Direction.DOWN) movablePoint = movablePoint.down();
                if (direction == Direction.LEFT) movablePoint = movablePoint.left();
                if (direction == Direction.RIGHT) movablePoint = movablePoint.right();
                movablePoints.add(movablePoint);
            } catch (PointOutOfBoardException ignored) {}
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
                throw new BlockedPathException();
            }
        }
    }

    private static boolean isToPointReachable(Point toPoint, Piece piece, Point movablePoint, Piece pieceOnMovablePoint) {
        return movablePoint.equals(toPoint) && piece.isOppositeWith(pieceOnMovablePoint);
    }
}
