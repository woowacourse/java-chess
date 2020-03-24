package domain.chesspiece;

import domain.team.Team;

public class Bishop extends Chesspiece {
    private static final String INITIAL = "B";

    public Bishop(Team team) {
        super(INITIAL, team);
    }
}
