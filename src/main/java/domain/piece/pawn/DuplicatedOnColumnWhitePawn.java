package domain.piece.pawn;

public class DuplicatedOnColumnWhitePawn extends WhitePawn {

    public static final float SCORE = 0.5f;

    @Override
    public float getScore() {
        return SCORE;
    }
}
