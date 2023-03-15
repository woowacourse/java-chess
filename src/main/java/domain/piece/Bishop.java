package domain.piece;

public final class Bishop extends Piece {

    private static final String NAME = "B";

    public Bishop() {
        super(NAME);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
