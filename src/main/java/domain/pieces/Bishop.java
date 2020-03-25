package domain.pieces;

import domain.team.Team;

public class Bishop extends Piece {
    private static final String INITIAL = "B";

    public Bishop(Team team) {
        super(INITIAL, team);
    }
}
