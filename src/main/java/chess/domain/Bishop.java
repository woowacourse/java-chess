package chess.domain;

public class Bishop extends Piece {
    private static final String NAME = "b";

    public Bishop(Team team) {
        super(team);
    }

    public String getName() {
        return NAME;
    }
}
