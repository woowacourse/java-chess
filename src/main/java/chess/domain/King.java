package chess.domain;

public class King extends Piece {
    private static final String NAME = "k";

    public King(Team team) {
        super(team);
    }

    public String getName() {
        return NAME;
    }
}
