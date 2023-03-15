package domain.piece;

public final class Bishop extends Piece {

    private static final String NAME = "B";

    public Bishop(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
