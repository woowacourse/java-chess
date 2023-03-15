package domain.piece;

public final class King extends Piece {

    private static final String NAME = "K";

    public King() {
        super(NAME);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
