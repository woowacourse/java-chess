package domain.piece;

public final class Rook extends Piece{

    private static final String NAME = "R";

    private Rook() {
        super(NAME);
    }

    public static Piece create() {
        return new Rook();
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
