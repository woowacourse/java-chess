package chess.board.piece;

import chess.board.Vector;

public class King extends Piece {

    public King(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Vector vector) {
        return vector.isRangeUnderAbsolute(1);
    }
}
