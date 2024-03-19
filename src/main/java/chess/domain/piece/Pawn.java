package chess.domain.piece;

public final class Pawn extends Piece {

    private static final String NAME = "P";

    public Pawn(Team team) {
        super(NAME, team);
    }
}
