package domain.chesspiece;

import domain.team.Team;

public class King extends Chesspiece {
    private static final String INITIAL = "K";

    public King(Team team) {
        super(INITIAL, team);
    }
}
