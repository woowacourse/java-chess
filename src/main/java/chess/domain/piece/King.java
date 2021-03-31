package chess.domain.piece;

import chess.domain.moveStrategy.KingMove;

public class King extends Division {
    public static final int KING_SCORE = 0;

    public King(Color color) {
        super(color, "k", new KingMove(color), new KingMove(color));
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double score() {
        return KING_SCORE;
    }
}
