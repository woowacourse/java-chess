package domain.piece;

import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;

import java.util.*;

public class Pieces {

    private final List<Piece> value;

    public Pieces(final List<Piece> value) {
        this.value = new ArrayList<>(value);
    }


    public Optional<Piece> findPieceWithPoint(final Point point) {
        return value.stream()
                    .filter(piece -> piece.isEqualPoint(point))
                    .findAny();
    }

    public boolean check(final Piece piece, final Point endPoint) {
        if (!piece.canMove(endPoint)) {
            return false;
        }
        return switch (piece.getStatus()) {
            case BISHOP, ROOK, QUEEN -> !(hasAnyPiece(piece.getPoint(), endPoint) && !isFriend(piece, endPoint));
            case KING, KNIGHT -> !isFriend(piece, endPoint);
            case PAWN ->
                    (piece.isDirectionStraight(endPoint) && !isInPiece(endPoint)) || (piece.isDirectionDiagonal(endPoint) && !isFriend(piece, endPoint));
        };
    }

    public void move(final Piece piece, final Point point) {
        final Optional<Piece> optionalPiece = findPieceWithPoint(point);
        optionalPiece.ifPresent(value::remove);
        piece.move(point);
    }


    public int size() {
        return value.size();
    }

    public List<Piece> allPieces() {
        return Collections.unmodifiableList(this.value);
    }

    private boolean isFriend(final Piece piece, final Point point) {
        final Optional<Piece> optionalPiece = findPieceWithPoint(point);
        if (optionalPiece.isEmpty()) {
            return false;
        }
        final Piece toPiece = optionalPiece.get();
        return piece.sameColor(toPiece);
    }

    private boolean hasAnyPiece(final Point startPoint, final Point endPoint) {
        final Direction direction = startPoint.calculate(endPoint);

        final List<Point> pathPoints = new ArrayList<>();
        Point pathPoint = direction.movePoint(startPoint);

        while (pathPoint.notEquals(endPoint) && direction.canMovePoint(pathPoint)) {
            pathPoints.add(pathPoint);
            pathPoint = direction.movePoint(pathPoint);
        }

        return pathPoints.stream()
                         .map(this::findPieceWithPoint)
                         .anyMatch(Optional::isPresent);
    }


    private boolean isInPiece(final Point endPoint) {
        return this.findPieceWithPoint(endPoint)
                   .isPresent();
    }
}
