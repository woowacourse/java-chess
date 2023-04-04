package domain.piece.pawn;

import domain.piece.Piece;

public abstract class Pawn extends Piece {

    public static final float SCORE = 1f;

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public float getScore() {
        return SCORE;
    }
}
