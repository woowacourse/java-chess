package domain.piece;

import domain.board.Position;

public final class Bishop extends Piece {

    private static final String NAME = "B";

    private Bishop(final Position position) {
        super(NAME, position);
    }

    public static Piece create(final Position position) {
        return new Bishop(position);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
