package chess.piece;

import chess.board.Position;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return false;
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
