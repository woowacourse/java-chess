package domain.piece;

public final class Rook extends Piece {

    private static final String NAME = "R";

    public Rook() {
        super(NAME);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
