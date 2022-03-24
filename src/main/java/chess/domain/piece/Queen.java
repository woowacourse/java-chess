package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.QueenMovableStrategy;

public final class Queen extends Piece {

    private static final String QUEEN_NAME = "Q";
    private static final double QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(color, QUEEN_NAME, new QueenMovableStrategy());
    }

    @Override
    public final double score() {
        return QUEEN_SCORE;
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
