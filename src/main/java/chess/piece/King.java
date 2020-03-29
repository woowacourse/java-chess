package chess.piece;

import chess.coordinate.Vector;

public class King extends Piece {

    private static final int KING_SCORE = 0;

    public King(final Team team) {
        super(team, KING_SCORE);
    }

    @Override
    protected boolean canReach(Vector vector, Piece targetPiece) {
        return vector.isRangeUnderAbsolute(1);
    }
}
