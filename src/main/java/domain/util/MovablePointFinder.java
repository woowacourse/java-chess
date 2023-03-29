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
                -> points.addAll(findPointsThroughDirection(from, to, direction, range, pieceStatus)));

        addPointsIfPawn(piece, from, pieceStatus, points);

        return points;
    }

    private static List<Point> findPointsThroughDirection(Point fromPoint, Point toPoint, Direction direction, Integer range, List<List<Piece>> pieceStatus) {
        List<Point> points = new ArrayList<>();
        Point current = fromPoint;
        for (int count = 0; count < range; count++) {
            current = movePointThroughDirection(current, direction);
            
            if (current.equals(toPoint)) {
                addPointIfReachable(points, current, findPiece(pieceStatus, fromPoint), findPiece(pieceStatus, toPoint));
                return points;
            }

            try {
                Piece piece = findPiece(pieceStatus, current);
                if (!piece.isEmpty()) {
                    points.clear();
                    return points;
                }
            } catch (IndexOutOfBoundsException ignored) {}

            points.add(current);
        }
        return points;
    }

    private static void addPointIfReachable(List<Point> points, Point current, Piece pieceOnFromPoint, Piece pieceOnTargetPoint) {
        if (!pieceOnTargetPoint.isEmpty() && !pieceOnFromPoint.isOppositeWith(pieceOnTargetPoint)) {
            points.clear();
            return;
        }
        points.add(current);
    }

    private static Point movePointThroughDirection(Point point, Direction direction) {
        try {
            point = point.move(direction);
        } catch (PointOutOfBoardException ignored) {}
        return point;
    }

    private static void addPointsIfPawn(Piece piece, Point from, List<List<Piece>> pieceStatus, List<Point> points) {
        try {
            addPointsIfBlackPawn(piece, from, pieceStatus, points);
            addPointsIfWhitePawn(piece, from, pieceStatus, points);
        } catch (PointOutOfBoardException ignore) {}
    }

    private static void addPointsIfWhitePawn(Piece piece, Point from, List<List<Piece>> pieceStatus, List<Point> points) {
        if (!piece.isWhitePawn()) {
            return;
        }

        Piece pieceLeftUp = findPiece(pieceStatus, from.leftUp());
        if (!pieceLeftUp.isEmpty() && piece.isOppositeWith(pieceLeftUp)) {
            points.add(from.leftUp());
        }

        Piece pieceRightUp = findPiece(pieceStatus, from.rightUp());
        if (!pieceRightUp.isEmpty() && piece.isOppositeWith(pieceRightUp)) {
            points.add(from.rightUp());
        }
    }

    private static void addPointsIfBlackPawn(Piece piece, Point from, List<List<Piece>> pieceStatus, List<Point> points) {
        if (!piece.isBlackPawn()) {
            return;
        }

        Piece pieceLeftDown = findPiece(pieceStatus, from.leftDown());
        if (!pieceLeftDown.isEmpty() && piece.isOppositeWith(pieceLeftDown)) {
            points.add(from.leftDown());
        }

        Piece pieceRightDown = findPiece(pieceStatus, from.rightDown());
        if (!pieceRightDown.isEmpty() && piece.isOppositeWith(pieceRightDown)) {
            points.add(from.rightDown());
        }
    }

    private static Piece findPiece(List<List<Piece>> pieceStatus, Point point) {
        return pieceStatus
                .get(point.findIndexFromBottom())
                .get(point.findIndexFromLeft());
    }
}
