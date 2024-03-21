package domain;

import domain.piece.attribute.point.Point;

import java.util.*;

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

    public Map<Point, Piece> toMap() {
        Map<Point, Piece> map = new HashMap<>();

        for (final Piece piece : value) {
            map.put(piece.getPoint(), piece);
        }
        return map;
    }

    public boolean isFriend(Piece piece, Point point) {
        Optional<Piece> optionalPiece = getPieceWithPoint(point);
        if (optionalPiece.isEmpty()) {
            return false;
        }
        Piece toPiece = optionalPiece.get();
        return piece.sameColor(toPiece);
    }
}
