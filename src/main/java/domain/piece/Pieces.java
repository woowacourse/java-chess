package domain.piece;

import domain.piece.attribute.point.Point;

import java.util.*;

public class Pieces {

    private final Set<Piece> value;

    public Pieces(final List<Piece> value) {
        this.value = new HashSet<>(value);
    }

    public Pieces(final Set<Piece> pieces) {
        this.value = new HashSet<>(pieces);
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
                    (piece.isDirectionStraight(endPoint) && !hasPiece(endPoint)) || (piece.isDirectionDiagonal(endPoint) && !isFriend(piece, endPoint));
        };
    }

    public void move(final Piece piece, final Point point) {
        final var optionalPiece = findPieceWithPoint(point);
        optionalPiece.ifPresent(value::remove);
        piece.move(point);
    }


    public int size() {
        return value.size();
    }

    public Map<Point, Piece> toMap() {
        final var map = new HashMap<Point, Piece>();

        for (final Piece piece : value) {
            map.put(piece.getPoint(), piece);
        }
        return map;
    }

    public boolean isFriend(Piece piece, Point point) {
        final var optionalPiece = findPieceWithPoint(point);
        return optionalPiece.filter(piece::sameColor).isPresent();
    }

    private boolean hasAnyPiece(Point startPoint, Point endPoint) {
        final var list = startPoint.toIndex()
                                   .findMovePath(startPoint.calculate(endPoint), endPoint.toIndex());

        return list.stream()
                   .map(index -> findPieceWithPoint(Point.fromIndex(index)))
                   .anyMatch(Optional::isPresent);
    }

    public boolean hasPiece(Point endPoint) {
        return this.findPieceWithPoint(endPoint)
                   .isPresent();
    }

    public boolean hasNothing(Point endPoint) {
        return this.findPieceWithPoint(endPoint)
                .isEmpty();
    }
}
