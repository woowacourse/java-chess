package chess.domain.result;

import chess.domain.piece.Piece;
import java.util.List;

public class BoardResult implements Result {

    private final List<Piece> pieces;

    public BoardResult(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
