package chess.board.piece;

import chess.board.Vector;

public class Rook extends Piece {

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Vector vector) {
        return vector.isStraight();
    }

    @Override
    public boolean isSameTeam(final Team team) {
        return false;
    }
}
