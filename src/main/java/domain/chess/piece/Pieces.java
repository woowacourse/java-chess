package domain.chess.piece;

import domain.chess.Point;

import java.util.*;

public class Pieces {

    private final List<Piece> value;

    public Pieces(final List<Piece> value) {
        this.value = new ArrayList<>(value);
    }

    public void move(final Piece piece, final Point point) {
        if (!piece.canMove(point, value)) {
            throw new IllegalStateException(String.format("%s를 %s로 이동할 수 없습니다.", piece, point));
        }
        final Optional<Piece> optionalPiece = findPieceWithPoint(point);
        optionalPiece.ifPresent(value::remove);
        piece.move(point);
    }

    public Optional<Piece> findPieceWithPoint(final Point point) {
        return value.stream()
                    .filter(piece -> piece.isEqualPoint(point))
                    .findAny();
    }

    public boolean containPieceWithPoint(final Point point) {
        return findPieceWithPoint(point).isPresent();
    }

    public int size() {
        return value.size();
    }

    public List<Piece> allPieces() {
        return Collections.unmodifiableList(this.value);
    }
}
