package domain.piece;

public final class King extends Piece {

    private static final String NAME = "K";

    public King(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
