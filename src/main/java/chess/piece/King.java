package chess.piece;

import chess.board.Position;

public class King extends Piece {

    public King(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
