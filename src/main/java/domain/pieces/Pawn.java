package domain.pieces;

import domain.move.Direction;
import domain.point.MovePoint;
import domain.point.Point;
import domain.team.Team;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    private static final String INITIAL = "P";

    private static final double score = 1;
    private static final Map<Team, List<Direction>> cache = new HashMap<>();

    static {
        cache.put(Team.BLACK, Direction.getBlackPawnDirection());
        cache.put(Team.WHITE, Direction.getWhitePawnDirection());
    }

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
    public List<Direction> getDirection(Team team) {
        return cache.get(team);
    }

    @Override
    public double getScore(Map<Point, Piece> pieces, Point point) {
        if (getSameColumnPawnCount(pieces, point) < 2) {
            return score;
        }
        return score * 0.5;
    }

    private int getSameColumnPawnCount(Map<Point, Piece> pieces, Point point) {
        return (int) pieces.keySet().stream()
            .filter(streamPoint -> pieces.get(streamPoint).getInitial().equals(initial))
            .filter(streamPoint -> isSameColumn(streamPoint, point))
            .count();
    }

    private boolean isSameColumn(Point streamPoint, Point point) {
        return streamPoint.getColumnIndex() == point.getColumnIndex();
    }
}
