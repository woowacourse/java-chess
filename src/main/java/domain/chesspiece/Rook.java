package domain.chesspiece;

import domain.team.Team;

public class Rook extends Chesspiece {
    private static final String INITIAL = "R";

    public Rook(Team team) {
        super(INITIAL, team);
    }
}
