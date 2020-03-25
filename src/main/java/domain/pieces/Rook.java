package domain.pieces;

import domain.team.Team;

public class Rook extends Piece {
    private static final String INITIAL = "R";

    public Rook(Team team) {
        super(INITIAL, team);
    }
}
