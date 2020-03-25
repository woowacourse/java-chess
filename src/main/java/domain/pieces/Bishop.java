package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public class Bishop extends Piece {
    private static final String INITIAL = "B";

    public Bishop(Team team, Point point) {
        super(INITIAL, team, point);
    }
}
