package domain.pieces;

import domain.point.Point;

import java.util.HashSet;
import java.util.Set;

public class Pieces {

    private Set<Piece> pieces;

    public Pieces(Set<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }

    public static Pieces of(PiecesFactory piecesFactory) {
        return new Pieces(piecesFactory.getInstance());
    }

    public void move(Point beforePoint, Point afterPoint) {

    }
}
