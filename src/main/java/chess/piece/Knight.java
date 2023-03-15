package chess.piece;

import chess.board.Position;

public class Knight extends Piece {

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return false;
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
