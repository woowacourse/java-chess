package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.RookMovableStrategy;

public final class Rook extends Piece {

    private static final String ROOK_NAME = "R";
    private static final double ROOK_SCORE = 5;

    public Rook(Color color) {
        super(color, ROOK_NAME, new RookMovableStrategy());
    }

    @Override
    public final double score() {
        return ROOK_SCORE;
    }

    @Override
    public final boolean isPawn() {
        return false;
    }

    @Override
    public final boolean isKing() {
        return false;
    }
}
