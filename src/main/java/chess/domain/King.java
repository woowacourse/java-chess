package chess.domain;

public class King extends Piece {
    private static final String NAME = "k";

    public King(Team team) {
        super(team, (a, b) -> false);
    }

    public String getName() {
        return NAME;
    }
}
