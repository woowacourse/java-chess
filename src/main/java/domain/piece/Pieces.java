package domain.piece;

import domain.piece.point.Point;

import java.util.List;
import java.util.Optional;

public class Pieces {

    private final List<Piece> value;

    public Pieces(final List<Piece> value) {
        this.value = value;
    }

    public Optional<Piece> getPieceWithPoint(final Point point) {
        return value.stream()
                    .filter(piece -> piece.isEqualPoint(point))
                    .findAny();
    }

}
