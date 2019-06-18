package chess.domain;

public class Knight extends Piece {
    private static final String NAME = "n";

    public Knight(Team team) {
        super(team, (a, b) -> false);
    }

    public String getName() {
        return NAME;
    }
}
