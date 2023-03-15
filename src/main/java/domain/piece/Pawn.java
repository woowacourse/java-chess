package domain.piece;

public final class Pawn extends Piece {

    private static final String NAME = "P";

    public Pawn(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
