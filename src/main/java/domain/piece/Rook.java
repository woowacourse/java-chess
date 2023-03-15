package domain.piece;

public final class Rook extends Piece {

    private static final String NAME = "R";

    public Rook(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
