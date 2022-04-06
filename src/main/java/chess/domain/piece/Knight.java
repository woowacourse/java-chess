package chess.domain.piece;

import static chess.domain.piece.Direction.*;

public class Knight extends Piece {

    private static final String name = "knight";
    private static final float score = 2.5f;

    public Knight(Team team) {
        super(name, pullTwoStraightAndDiagonalDirections(), team);
    }

    @Override
    public float getScore() {
        return score;
    }
}
