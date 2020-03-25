package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public class Empty extends Piece {
    private static final String INITIAL = ".";

    public Empty(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Point from, Point to) {
        return false;
    }
}
