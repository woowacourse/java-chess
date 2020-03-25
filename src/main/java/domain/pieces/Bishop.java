package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public class Bishop extends Piece {
    private static final String INITIAL = "B";

    public Bishop(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Point from, Point to) {
        return false;
    }
}
