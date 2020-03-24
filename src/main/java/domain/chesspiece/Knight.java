package domain.chesspiece;

import domain.team.Team;

public class Knight extends Chesspiece {
    private static final String INITIAL = "N";

    public Knight(Team team) {
        super(INITIAL, team);
    }
}
