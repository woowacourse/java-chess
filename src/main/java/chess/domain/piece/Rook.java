package chess.domain.piece;

import chess.domain.coordinate.Vector;

public class Rook extends Piece {

    private static final int ROOK_SCORE = 5;

    public Rook(final Team team) {
        super(team, ROOK_SCORE);
    }

    @Override
    public boolean canMove(final Vector vector, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return vector.isStraight();
    }
}
