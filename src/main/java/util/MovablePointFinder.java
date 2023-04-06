package util;

import dao.Movement;
import domain.piece.Piece;
import domain.point.Direction;
import domain.point.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MovablePointFinder {
    private MovablePointFinder() {
    }

    public static List<Point> addPoints(Movement movement, Map<Point, Piece> pieceStatus) {
        final List<Point> points = new ArrayList<>();
        Piece piece = pieceStatus.get(movement.getStartingPoint());
        Map<Direction, Integer> directionsAndRanges = piece.getMovableDirectionAndRange();
        directionsAndRanges.forEach((direction, range)
                -> points.addAll(findPointsThroughDirection(movement, direction, range, pieceStatus)));
        addPointsIfPawn(piece, movement.getStartingPoint(), pieceStatus, points);
        return points;
    }

    private static List<Point> findPointsThroughDirection(Movement movement, Direction direction, Integer range, Map<Point, Piece> pieceStatus) {
        List<Point> points = new ArrayList<>();
        Point currentPoint = movement.getStartingPoint();
        for (int count = 0; count < range; count++) {
            Optional<Point> point = findPointThroughDirection(currentPoint, movement, direction, pieceStatus);
            if (point.isPresent()) {
                points.add(point.get());
                currentPoint = point.get();
            }
        }
        return points;
    }

    private static Optional<Point> findPointThroughDirection(Point previousPoint, Movement movement, Direction direction, Map<Point, Piece> pieceStatus) {
        Point point = movePointThroughDirection(previousPoint, direction);
        // 한 칸 앞으로

        if (point.equals(movement.getDestinationPoint())) {
            Piece pieceOnStartingPoint = pieceStatus.get(movement.getStartingPoint());
            Piece pieceOnDestinationPoint = pieceStatus.get(movement.getDestinationPoint());
            if (isDestinationReachable(pieceOnStartingPoint, pieceOnDestinationPoint)) {
                return Optional.of(point);
            }
        }
        // 목적지와 같은 칸

        Piece piece = pieceStatus.get(point);
        if (!piece.isEmpty()) {
            return Optional.empty();
        }
        // 목적지로 가는 도중의 길

        return Optional.of(point);
    }

    private static boolean isDestinationReachable(Piece piece, Piece pieceOnDestination) {
        return pieceOnDestination.isEmpty() || piece.isOppositeWith(pieceOnDestination);
    }

    private static Point movePointThroughDirection(Point point, Direction direction) {
        try {
            point = point.move(direction);
        } catch (IllegalArgumentException ignored) {}
        return point;
    }

    private static void addPointsIfPawn(Piece piece, Point from, Map<Point, Piece> pieceStatus, List<Point> points) {
        try {
            addPointsIfBlackPawn(piece, from, pieceStatus, points);
            addPointsIfWhitePawn(piece, from, pieceStatus, points);
        } catch (IllegalArgumentException ignore) {}
    }

    private static void addPointsIfWhitePawn(Piece piece, Point from, Map<Point, Piece> pieceStatus, List<Point> points) {
        if (!piece.isWhitePawn()) {
            return;
        }

        Piece pieceLeftUp = pieceStatus.get(from.leftUp());
        if (!pieceLeftUp.isEmpty() && piece.isOppositeWith(pieceLeftUp)) {
            points.add(from.leftUp());
        }

        Piece pieceRightUp = pieceStatus.get(from.rightUp());
        if (!pieceRightUp.isEmpty() && piece.isOppositeWith(pieceRightUp)) {
            points.add(from.rightUp());
        }
    }

    private static void addPointsIfBlackPawn(Piece piece, Point startingPoint, Map<Point, Piece> pieceStatus, List<Point> points) {
        if (!piece.isBlackPawn()) {
            return;
        }

        Piece pieceLeftDown = pieceStatus.get(startingPoint.leftDown());
        if (!pieceLeftDown.isEmpty() && piece.isOppositeWith(pieceLeftDown)) {
            points.add(startingPoint.leftDown());
        }

        Piece pieceRightDown = pieceStatus.get(startingPoint.rightDown());
        if (!pieceRightDown.isEmpty() && piece.isOppositeWith(pieceRightDown)) {
            points.add(startingPoint.rightDown());
        }
    }
}
