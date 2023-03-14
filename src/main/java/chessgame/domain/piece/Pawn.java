package chessgame.domain.piece;

import chessgame.domain.Team;

public class Pawn implements Piece {
    private static final String ORIGINAL_NAME = "p";

    private final String name;

    private Pawn(String name) {
        this.name = name;
    }

    public static Pawn from(Team team) {
        return new Pawn(team.calculate(ORIGINAL_NAME));
    }

    @Override
    public String toString() {
        return name;
    }
}
