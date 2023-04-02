package domain.util;

import domain.exception.PointOutOfBoardException;
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

    public static List<Point> addPoints(Point startingPoint, Point destinationPoint, List<List<Piece>> pieceStatus) {
        final List<Point> points = new ArrayList<>();
        Optional<Piece> pieceOptional = findPiece(pieceStatus, startingPoint);
        if (pieceOptional.isPresent()) {
            Piece piece = pieceOptional.get();
            Map<Direction, Integer> directionsAndRanges = piece.getMovableDirectionAndRange();
            directionsAndRanges.forEach((direction, range)
                    -> points.addAll(findPointsThroughDirection(startingPoint, destinationPoint, direction, range, pieceStatus)));
            addPointsIfPawn(piece, startingPoint, pieceStatus, points);
        }
        return points;
    }

    private static List<Point> findPointsThroughDirection(Point startingPoint, Point destinationPoint, Direction direction, Integer range, List<List<Piece>> pieceStatus) {
        List<Point> points = new ArrayList<>();
        Point currentPoint = startingPoint;
        for (int count = 0; count < range; count++) {
            Optional<Point> point = findPointThroughDirection(currentPoint, startingPoint, destinationPoint, direction, pieceStatus);
            if (point.isPresent()) {
                points.add(point.get());
                currentPoint = point.get();
            }
        }
        return points;
    }

    private static Optional<Point> findPointThroughDirection(Point previousPoint, Point startingPoint, Point destinationPoint, Direction direction, List<List<Piece>> pieceStatus) {
        Point point = movePointThroughDirection(previousPoint, direction);
        // 한 칸 앞으로

        if (point.equals(destinationPoint)) {
            Optional<Piece> pieceOnStartingPoint = findPiece(pieceStatus, startingPoint);
            Optional<Piece> pieceOnDestinationPoint = findPiece(pieceStatus, destinationPoint);
            if (pieceOnStartingPoint.isPresent() &&
                    pieceOnDestinationPoint.isPresent() &&
                    isDestinationReachable(pieceOnStartingPoint.get(), pieceOnDestinationPoint.get())) {
                return Optional.of(point);
            }
        }
        // 목적지와 같은 칸

        Optional<Piece> piece = findPiece(pieceStatus, point);
        if (piece.isPresent() && !piece.get().isEmpty()) {
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

        Optional<Piece> pieceLeftUp = findPiece(pieceStatus, from.leftUp());
        if (pieceLeftUp.isPresent() && !pieceLeftUp.get().isEmpty() && piece.isOppositeWith(pieceLeftUp.get())) {
            points.add(from.leftUp());
        }

        Optional<Piece> pieceRightUp = findPiece(pieceStatus, from.rightUp());
        if (pieceRightUp.isPresent() && !pieceRightUp.get().isEmpty() && piece.isOppositeWith(pieceRightUp.get())) {
            points.add(from.rightUp());
        }
    }

    private static void addPointsIfBlackPawn(Piece piece, Point startingPoint, List<List<Piece>> pieceStatus, List<Point> points) {
        if (!piece.isBlackPawn()) {
            return;
        }

        Optional<Piece> pieceLeftDown = findPiece(pieceStatus, startingPoint.leftDown());
        if (pieceLeftDown.isPresent() && !pieceLeftDown.get().isEmpty() && piece.isOppositeWith(pieceLeftDown.get())) {
            points.add(startingPoint.leftDown());
        }

        Optional<Piece> pieceRightDown = findPiece(pieceStatus, startingPoint.rightDown());
        if (pieceRightDown.isPresent() && !pieceRightDown.get().isEmpty() && piece.isOppositeWith(pieceRightDown.get())) {
            points.add(startingPoint.rightDown());
        }
    }

    private static Optional<Piece> findPiece(List<List<Piece>> pieceStatus, Point point) {
        try {
            return Optional.of(pieceStatus
                    .get(point.findIndexFromBottom())
                    .get(point.findIndexFromLeft()));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
