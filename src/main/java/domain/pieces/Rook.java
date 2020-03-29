package domain.pieces;

import domain.move.Direction;
import domain.point.MovePoint;
import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public class Rook extends Piece {
    private static final String INITIAL = "R";

    public Rook(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Direction direction, Map<Point, Piece> pieces, MovePoint movePoint) {
        return Roles.isMovableUnlimitedCase(direction, pieces, movePoint);
    }

    @Override
    public boolean isNoneTeam() {
        return false;
    }
}
