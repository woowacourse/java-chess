package chess.domain.piece;

import static chess.domain.piece.Direction.*;

public class King extends Piece {

    private static final String name = "king";
    private static final float score = 0.0f;

    public King(Team team) {
        super(name, pullAllBasicDirections(), team);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public float getScore() {
        return score;
    }
}
