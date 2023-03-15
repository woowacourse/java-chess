package domain.piece;

public final class Queen extends Piece {

    private static final String NAME = "Q";

    public Queen(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
