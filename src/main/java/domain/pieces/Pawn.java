package domain.pieces;

import domain.move.Direction;
import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public class Pawn extends Piece {

    private static final String INITIAL = "P";

    public Pawn(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Direction direction, Map<Point, Piece> pieces, Point from, Point to) {
        return isMovableDirection(direction, pieces, from, to);
    }

    private boolean isMovableDirection(Direction direction, Map<Point, Piece> pieces, Point from, Point to) {
        if (Roles.isMovableLimitedCase(direction, pieces, from, to)) {
            return isLinearOrDiagonal(direction, pieces, to);
        }
        return false;
    }

    private boolean isLinearOrDiagonal(Direction direction, Map<Point, Piece> pieces, Point to) {
        if (team == Team.BLACK) {
            return Roles.isLinearOrDiagonalBlackTeam(direction, pieces, to);
        }
        return Roles.isLinearOrDiagonalWhiteTeam(direction, pieces, to);
    }
}
