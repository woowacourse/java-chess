package domain.piece;

public final class King extends Piece{

    private static final String NAME = "K";

    private King() {
        super(NAME);
    }

    public static Piece create() {
        return new King();
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
