package chess.board.piece;

import chess.board.Vector;

public class King extends Piece {

    private static final int KING_SCORE = 0;

    public King(final Team team) {
        super(team, KING_SCORE);
    }

    @Override
    public boolean canMove(final Vector vector, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return vector.isRangeUnderAbsolute(1);
    }
}
