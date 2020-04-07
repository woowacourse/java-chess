package chess.domain.piece;

import chess.domain.coordinate.Vector;

public class Bishop extends Piece {

    private static final int BISHOP_SCORE = 3;

    public Bishop(final Team team) {
        super(team, BISHOP_SCORE);
    }

    @Override
    public boolean canMove(final Vector vector, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return vector.isDiagonal();
    }
}
