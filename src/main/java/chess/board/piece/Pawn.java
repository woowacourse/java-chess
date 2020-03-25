package chess.board.piece;

import chess.board.Vector;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Vector vector) {
        return vector.isRangeUnderAbsolute(1) && team.isSameDirection(vector.getRankVariation());
    }
}
