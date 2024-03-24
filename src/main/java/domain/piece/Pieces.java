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

    public boolean containPieceWithPoint(final Point point) {
        return findPieceWithPoint(point).isPresent();
    }

    public boolean check(final Piece piece, final Point endPoint) {
        return piece.canMove(endPoint, value);
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
}
