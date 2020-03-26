package chess.board.piece;

import chess.board.Vector;

public class Pawn extends Piece {

    private static final int PAWN_SCORE = 1;

    public Pawn(final Team team) {
        super(team, PAWN_SCORE);
    }

    @Override
    public boolean canMove(final Vector vector) {
        return vector.isRangeUnderAbsolute(1) && team.isSameDirection(vector.getRankVariation());
    }
}
