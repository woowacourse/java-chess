package domain.piece;

public final class Knight extends Piece {

    private static final String NAME = "N";

    public Knight() {
        super(NAME);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
