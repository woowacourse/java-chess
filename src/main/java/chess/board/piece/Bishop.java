package chess.board.piece;

import chess.board.Vector;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Vector vector) {
        return vector.isDiagonal();
    }
}
