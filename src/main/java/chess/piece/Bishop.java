package chess.piece;

import chess.coordinate.Vector;

public class Bishop extends Piece {

    private static final int SCORE = 3;

    public Bishop(final Team team) {
        super(team, SCORE);
    }

    @Override
    protected boolean canReach(Vector vector, Piece targetPiece) {
        return vector.isDiagonal();
    }
}
