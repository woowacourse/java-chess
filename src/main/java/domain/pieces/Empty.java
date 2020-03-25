package domain.pieces;

import domain.team.Team;

public class Empty extends Piece {
    private static final String INITIAL = ".";

    public Empty(Team team) {
        super(INITIAL, team);
    }
}
