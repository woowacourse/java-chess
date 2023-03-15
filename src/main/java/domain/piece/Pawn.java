package domain.piece;

public final class Pawn extends Piece {

    private static final String NAME = "P";

    public Pawn() {
        super(NAME);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
