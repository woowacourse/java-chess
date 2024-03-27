package domain.piece;

import domain.piece.attribute.point.Point;

import dto.PiecesDto;
import java.util.*;

public class Pieces {
    private final Set<Piece> values;
    public Pieces(final Set<Piece> pieces) {
        this.values = new HashSet<>(pieces);
    }

    public boolean canReplace(final Piece piece, final Point endPoint) {
        return piece.findLegalMovePoints(this).contains(endPoint);
    }

    public void replace(final Piece piece, final Point endPoint) {
        Optional<Piece> pieceWithPoint = findPieceWithPoint(endPoint);
        pieceWithPoint.ifPresent(values::remove);
        values.remove(piece);
        Piece moved = piece.move(endPoint, this);
        values.add(moved);
    }

    public Optional<Piece> findPieceWithPoint(final Point point) {
        return values.stream()
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

    public PiecesDto toDto() {
        return new PiecesDto(values.stream()
                .map(Piece::toDto)
                .toList());
    }

    public boolean isEmpty() {
        return this.values.isEmpty();
    }
}
