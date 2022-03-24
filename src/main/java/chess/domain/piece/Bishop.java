package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.BishopMovableStrategy;

public final class Bishop extends Piece {

    private static final String BISHOP_NAME = "B";
    private static final double BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(color, BISHOP_NAME, new BishopMovableStrategy());
    }

    @Override
    public final double score() {
        return BISHOP_SCORE;
    }
}
