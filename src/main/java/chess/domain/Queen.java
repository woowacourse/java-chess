package chess.domain;

public class Queen extends Piece {
    private static final String NAME = "q";

    public Queen(Team team) {
        super(team, (a, b) -> false);
    }

    public String getName() {
        return NAME;
    }
}
