package domain.pieces;

import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public class King extends Piece {
    private static final String INITIAL = "K";

    public King(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Map<Point, Piece> pieces, Point from, Point to) {
        return false;
    }
}
