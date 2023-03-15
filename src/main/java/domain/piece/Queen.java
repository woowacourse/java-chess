package domain.piece;

public final class Queen extends Piece {

    private static final String NAME = "Q";

    public Queen() {
        super(NAME);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
