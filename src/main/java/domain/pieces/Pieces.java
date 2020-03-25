package domain.pieces;

import java.util.HashSet;
import java.util.Set;

public class Pieces {

    private Set<Piece> pieces;

    public Pieces(Set<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }
}
