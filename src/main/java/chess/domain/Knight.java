package chess.domain;

public class Knight extends Piece {
    private static final String NAME = "n";

    public Knight(Team team) {
        super(team);
    }

    public String getName() {
        return NAME;
    }
}
