package chess.domain.piece;

import chess.domain.Color;

public final class Queen extends Piece {
    private static final int QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(color);
    }

    @Override
    public double getScore() {
        return QUEEN_SCORE;
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
