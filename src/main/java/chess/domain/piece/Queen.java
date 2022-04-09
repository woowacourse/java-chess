package chess.domain.piece;

import static chess.domain.piece.Direction.*;

public class Queen extends Piece {

    private static final String name = "queen";
    private static final float score = 9.0f;

    public Queen(Team team) {
        super(name, pullAllBasicDirections(), team);
    }

    @Override
    public boolean isOneStep() {
        return false;
    }

    @Override
    public float getScore() {
        return score;
    }
}