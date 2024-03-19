package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {
    private final Map<Square, Piece> pieces;

    public Board(Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public Map<Square, Piece> getPieces() {
        return pieces;
    }
}
