package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.strategy.RookMoveStrategy;

public class Rook extends Piece {
    private static final Notation ROOK_NOTATION = new Notation("R");

    public Rook(Color color) {
        super(color, ROOK_NOTATION, new RookMoveStrategy());
    }
}
