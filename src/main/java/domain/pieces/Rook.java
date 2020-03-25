package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public class Rook extends Piece {
    private static final String INITIAL = "R";

    public Rook(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Point from, Point to) {
        return false;
    }
}
