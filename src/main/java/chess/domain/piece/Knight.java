package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.KnightMovableStrategy;

public final class Knight extends Piece {

    private static final String KNIGHT_NAME = "N";
    private static final double KNIGHT_SCORE = 2.5;

    public Knight(Color color) {
        super(color, KNIGHT_NAME, new KnightMovableStrategy());
    }

    @Override
    public final double score() {
        return KNIGHT_SCORE;
    }

    @Override
    public final boolean isPawn() {
        return false;
    }
}
