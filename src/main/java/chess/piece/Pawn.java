package chess.piece;

import chess.board.Position;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
