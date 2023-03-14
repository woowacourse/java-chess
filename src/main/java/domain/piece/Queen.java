package domain.piece;

public final class Queen extends Piece{

    private static final String NAME = "Q";

    private Queen() {
        super(NAME);
    }

    public static Piece create() {
        return new Queen();
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
