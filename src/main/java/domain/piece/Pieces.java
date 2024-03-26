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


    public boolean canMove(final Piece piece, final Point endPoint) {
        return piece.findLegalMovePoints(this).contains(endPoint);
    }

    public void replace(final Piece piece, final Point endPoint) {
        Optional<Piece> pieceWithPoint = findPieceWithPoint(endPoint);
        pieceWithPoint.ifPresent(value::remove);
        value.remove(piece);
        Piece moved = piece.move(endPoint, this);
        value.add(moved);
    }

    public Optional<Piece> findPieceWithPoint(final Point point) {
        return value.stream()
                .filter(piece -> piece.isEqualPoint(point))
                .findAny();
    }

    public boolean isFriend(Piece piece, Point point) {
        final var optionalPiece = findPieceWithPoint(point);
        return optionalPiece.filter(piece::isSameColor).isPresent();
    }

    public boolean hasPiece(Point endPoint) {
        return this.findPieceWithPoint(endPoint)
                   .isPresent();
    }

    public boolean hasNothing(Point endPoint) {
        return this.findPieceWithPoint(endPoint)
                .isEmpty();
    }

    public Map<Point, Piece> toMap() {
        final var map = new HashMap<Point, Piece>();

        for (final Piece piece : value) {

            map.put(piece.getPoint(), piece);
        }
        return map;
    }
}
