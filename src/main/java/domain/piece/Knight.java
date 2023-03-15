package domain.piece;

public final class Knight extends Piece {

    private static final String NAME = "N";

    public Knight(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
