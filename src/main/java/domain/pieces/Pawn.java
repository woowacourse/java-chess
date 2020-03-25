package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public class Pawn extends Piece {
    private static final String INITIAL = "P";

    public Pawn(Team team) {
        super(INITIAL, team);
    }
}
