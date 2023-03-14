package domain.piece;

public final class Pawn extends Piece{

    private static final String NAME = "P";

    private Pawn() {
        super(NAME);
    }

    public static Piece create() {
        return new Pawn();
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
