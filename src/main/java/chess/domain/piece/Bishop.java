package chess.domain.piece;

import static chess.domain.piece.Direction.*;

public class Bishop extends Piece {

    private static final String name = "bishop";
    private static final float score = 3.0f;

    public Bishop(Team team) {
        super(name, pullDiagonalDirections(), team);
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
