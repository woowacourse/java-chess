package domain.pieces;

import domain.team.Team;

public class Queen extends Piece {
    private static final String INITIAL = "Q";

    public Queen(Team team) {
        super(INITIAL, team);
    }
}
