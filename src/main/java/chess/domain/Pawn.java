package chess.domain;

public class Pawn extends Piece {
    private static final String NAME = "p";

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
