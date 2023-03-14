package domain.piece;

public final class Knight extends Piece{

    private static final String NAME = "N";

    private Knight() {
        super(NAME);
    }

    public static Piece create() {
        return new Knight();
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
