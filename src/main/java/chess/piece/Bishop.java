package chess.piece;

import chess.coordinate.Vector;

public class Bishop extends Piece {

    private static final int BISHOP_SCORE = 3;

    public Bishop(final Team team) {
        super(team, BISHOP_SCORE);
    }

    @Override
    protected boolean canReach(Vector vector, Piece targetPiece) {
        return vector.isDiagonal();
    }
}
