package domain.piece;

public final class Bishop extends Piece{

    private static final String NAME = "B";

    private Bishop() {
        super(NAME);
    }

    public static Piece create() {
        return new Bishop();
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
