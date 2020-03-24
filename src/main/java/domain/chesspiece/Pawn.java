package domain.chesspiece;

import domain.team.Team;

public class Pawn extends Chesspiece {
    private static final String INITIAL = "P";

    public Pawn(Team team) {
        super(INITIAL, team);
    }
}
