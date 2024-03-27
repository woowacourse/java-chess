package chess.domain.piece;

import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Pawn extends Piece {

    private static final int TWO_RANK = 2;
    private static final Map<Team, Pawn> POOL = Map.of(
            Team.WHITE, new Pawn(Team.WHITE),
            Team.BLACK, new Pawn(Team.BLACK)
    );

    private Pawn(Team team) {
        super(team);
    }

    static Pawn from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        if (invalidForwardDirection(currentPoint, nextPoint)) {
            return false;
        }
        return canAttack(currentPoint, nextPoint, target) || canForward(currentPoint, nextPoint, target);
    }

    private boolean invalidForwardDirection(Point currentPoint, Point nextPoint) {
        List<Point> movablePoints = findMovablePoints(currentPoint, getTeam());

        return !movablePoints.contains(nextPoint);
    }

    private List<Point> findMovablePoints(Point currentPoint, Team team) {
        List<Point> points = new ArrayList<>();
        int forwardDirection = team.forwardDirection();

        if (isStartPosition(currentPoint)) {
            findMovablePoint(points, currentPoint,
                    Direction.of(0, TWO_RANK * forwardDirection));
        }
        for (Direction direction : Direction.findPawnDirections()) {
            findMovablePoint(points, currentPoint,
                    Direction.of(direction.file(), direction.rank() * forwardDirection));
        }
        return points;
    }

    private boolean isStartPosition(Point currentPoint) {
        return currentPoint.isSameRank(Rank.SECOND) || currentPoint.isSameRank(Rank.SEVENTH);
    }

    private void findMovablePoint(List<Point> points, Point currentPoint, Direction direction) {
        if (currentPoint.addable(direction)) {
            Point point = currentPoint.move(direction);
            points.add(point);
        }
    }

    private boolean canAttack(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isSlopeOneDiagonal(nextPoint)
                && target != Piece.empty() && !target.isSameTeam(getTeam());
    }

    private boolean canForward(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isStraight(nextPoint)
                && target == Piece.empty();
    }
}
