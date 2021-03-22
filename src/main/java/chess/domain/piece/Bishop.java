package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.strategy.BishopMoveStrategy;

public class Bishop extends RealPiece {
    private static final Notation BISHOP_NOTATION = new Notation("B");

    public Bishop(Color color) {
        super(color, BISHOP_NOTATION, new BishopMoveStrategy());
    }
}
