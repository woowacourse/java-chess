package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.postion.Position;

import java.util.Map;

public class Board {
    private Map<Position, Piece> pieces;

    public Board(final Map<Position, Piece> pieces) {
       this.pieces = pieces;
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
