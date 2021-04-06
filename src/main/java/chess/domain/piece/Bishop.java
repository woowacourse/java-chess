package chess.domain.piece;

import chess.domain.piece.strategy.BishopMoveStrategy;

public class Bishop extends RealPiece {
    private static final String BISHOP_NAME = "Bishop";

    public Bishop(Color color) {
        super(color, BISHOP_NAME, new BishopMoveStrategy());
    }
}
