package domain.pieces;

import domain.move.Direction;
import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public class Knight extends Piece {
    private static final String INITIAL = "N";

    public Knight(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Direction direction, Map<Point, Piece> pieces, Point from, Point to) {
        return Roles.isMovableLimitedCase(direction, pieces, from, to);
    }
}
