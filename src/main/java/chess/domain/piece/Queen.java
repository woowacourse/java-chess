package chess.domain.piece;

import chess.domain.coordinate.Vector;

public class Queen extends Piece {

    private static final int QUEEN_SCORE = 9;

    public Queen(final Team team) {
        super(team, QUEEN_SCORE);
    }

    @Override
    public boolean canMove(final Vector vector, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return vector.isDiagonal() || vector.isStraight();
    }
}
