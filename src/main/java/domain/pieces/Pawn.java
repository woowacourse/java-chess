package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public class Pawn extends Piece {
    private static final String INITIAL = "P";

    public Pawn(Team team, Point point) {
        super(INITIAL, team, point);
    }
}
