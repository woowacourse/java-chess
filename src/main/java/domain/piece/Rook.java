package domain.piece;

import domain.board.Position;

public final class Rook extends Piece {

    private static final String NAME = "R";

    private Rook(final Position position) {
        super(NAME, position);
    }

    public static Piece create(final Position position) {
        return new Rook(position);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
