package domain.pieces;

import domain.point.Point;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pieces {

    private Map<Point, Piece> pieces;

    private Pieces(Map<Point, Piece> pieces) {
        this.pieces = new LinkedHashMap<>(pieces);
    }

    public static Pieces of(Map<Point, Piece> pieces) {
        return new Pieces(pieces);
    }

    public Map<Point, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public Piece getPiece(Point point) {
        return pieces.get(point);
    }

    public boolean isGetPoint(Point point) {
        return pieces.containsKey(point);
    }
}
