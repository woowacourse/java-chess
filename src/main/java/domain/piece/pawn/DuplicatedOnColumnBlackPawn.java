package domain.piece.pawn;

public class DuplicatedOnColumnBlackPawn extends BlackPawn {

    public static final float SCORE = 0.5f;

    @Override
    public float getScore() {
        return SCORE;
    }
}
