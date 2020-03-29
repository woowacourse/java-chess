package chess.piece;

import chess.coordinate.Vector;

public class King extends Piece {

    private static final int SCORE = 0;

    public King(final Team team) {
        super(team, SCORE);
    }

    @Override
    protected boolean canReach(Vector vector, Piece targetPiece) {
        return vector.isRangeUnderAbsolute(1);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
