package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public class Knight extends Piece {
    private static final String INITIAL = "N";

    public Knight(Team team) {
        super(INITIAL, team);
    }
}
