package domain.pieces;

import domain.move.Direction;
import domain.point.MovePoint;
import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public class Pawn extends Piece {

    private static final String INITIAL = "P";

    private static final double score = 1;

    public Pawn(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Direction direction, Map<Point, Piece> pieces, MovePoint movePoint) {
        return isMovableDirection(direction, pieces, movePoint);
    }

    @Override
    public boolean isNoneTeam() {
        return false;
    }

    private boolean isMovableDirection(Direction direction, Map<Point, Piece> pieces,
        MovePoint movePoint) {
        if (Roles.isMovableLimitedCase(direction, movePoint)) {
            return isLinearOrDiagonal(direction, pieces, movePoint.getTo());
        }
        return false;
    }

    private boolean isLinearOrDiagonal(Direction direction, Map<Point, Piece> pieces, Point to) {
        if (team == Team.BLACK) {
            return Roles.isLinearOrDiagonalBlackTeam(direction, pieces, to);
        }
        return Roles.isLinearOrDiagonalWhiteTeam(direction, pieces, to);
    }

    @Override
    public double getScore() {
        return score;
    }
}
