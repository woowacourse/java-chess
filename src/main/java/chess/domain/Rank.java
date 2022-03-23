package chess.domain;

import java.util.List;

public class Rank {
    List<Piece> pieces;

    public Rank(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
