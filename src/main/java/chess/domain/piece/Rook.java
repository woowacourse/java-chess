package chess.domain.piece;

import chess.domain.Color;

public final class Rook extends Piece {
    private static final int ROOK_SCORE = 5;

    public Rook(Color color) {
        super(color);
    }

    @Override
    public double getScore() {
        return ROOK_SCORE;
    }

    @Override
    public boolean isRook() {
        return true;
    }
}
