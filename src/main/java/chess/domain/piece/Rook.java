package chess.domain.piece;

import static chess.domain.piece.Direction.*;

public class Rook extends Piece {

    private static final String name = "R";
    private static final float score = 5.0f;

    public Rook(Team team) {
        super(name, pullStraightDirections(), team);
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
