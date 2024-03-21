package domain.piece;

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

    public boolean check(Piece piece, Point endPoint) {
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

    public void move(Piece piece, Point point) {
        final Optional<Piece> optionalPiece = findPieceWithPoint(point);
        optionalPiece.ifPresent(value::remove);
        piece.move(point);
    }


    public int size() {
        return value.size();
    }

    public Map<Point, Piece> toMap() {
        Map<Point, Piece> map = new HashMap<>();

        for (final Piece piece : value) {
            map.put(piece.getPoint(), piece);
        }
        return map;
    }

    private boolean isFriend(Piece piece, Point point) {
        Optional<Piece> optionalPiece = findPieceWithPoint(point);
        if (optionalPiece.isEmpty()) {
            return false;
        }
        Piece toPiece = optionalPiece.get();
        return piece.sameColor(toPiece);
    }

    private boolean hasAnyPiece(Point startPoint, Point endPoint) {
        final var list = startPoint.toIndex()
                                   .findMovePath(startPoint.calculate(endPoint), endPoint.toIndex());

        return list.stream()
                   .map(index -> findPieceWithPoint(Point.fromIndex(index)))
                   .anyMatch(Optional::isPresent);
    }


    private boolean isInPiece(Point endPoint) {
        return this.findPieceWithPoint(endPoint)
                   .isPresent();
    }
}
