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
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        Team team = getTeam();
        List<Point> movablePoints = findMovablePoints(currentPoint, team);

        return movablePoints.contains(nextPoint);
    }

    private List<Point> findMovablePoints(Point currentPoint, Team team) {
        List<Point> points = new ArrayList<>();
        int forwardDirection = team.forwardDirection();

        if (isStartPosition(currentPoint)) {
            findMovablePoint(points, currentPoint, 0, TWO_RANK * forwardDirection);
        }
        for (Direction direction : Direction.findPawnDirections()) {
            findMovablePoint(points, currentPoint, direction.file(), direction.rank() * forwardDirection);
        }
        return points;
    }

    private boolean isStartPosition(Point currentPoint) {
        return currentPoint.isSameRank(Rank.SECOND) || currentPoint.isSameRank(Rank.SEVENTH);
    }

    private void findMovablePoint(List<Point> points, Point currentPoint, int addFile, int addRank) {
        if (currentPoint.addable(addFile, addRank)) {
            Point point = currentPoint.add(addFile, addRank);
            points.add(point);
        }
    }
}
