package chess.domain.piece;

import java.util.Collections;
import java.util.List;

public class Pieces {
    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }
}
