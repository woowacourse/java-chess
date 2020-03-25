package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public class King extends Piece {
    private static final String INITIAL = "K";

    public King(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Point from, Point to) {
        return false;
    }
}
