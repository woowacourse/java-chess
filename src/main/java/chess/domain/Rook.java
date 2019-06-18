package chess.domain;

public class Rook extends Piece {
    private static final String NAME = "r";

    public Rook(Team team) {
        super(team, (a, b) -> false);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
