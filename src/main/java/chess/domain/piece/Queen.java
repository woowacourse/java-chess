package chess.domain.piece;

import static chess.domain.piece.Direction.*;

public class Queen extends Piece {

    private static final String name = "Q";
    private static final float score = 9.0f;

    public Queen(Team team) {
        super(name, pullAllBasicDirections(), team);
    }

    @Override
    public boolean isStep() {
        return false;
    }

    @Override
    public float getScore() {
        return score;
    }
}