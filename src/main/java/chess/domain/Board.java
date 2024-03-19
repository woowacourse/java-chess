package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
