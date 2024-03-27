package chess.domain.piece;

import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class Pawn extends Piece {

    private static final Map<Team, Pawn> POOL = Map.of(Team.WHITE, new Pawn(Team.WHITE), Team.BLACK,
            new Pawn(Team.BLACK));

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
        List<Point> movablePoints = findMovablePoints(currentPoint);

        return !movablePoints.contains(nextPoint);
    }

    private List<Point> findMovablePoints(Point currentPoint) {
        List<Point> points = new ArrayList<>();

        addStartPositionMovablePoint(currentPoint).ifPresent(points::add);
        points.addAll(addIfWhitePawnMovablePoints(currentPoint));
        points.addAll(addIfBlackPawnMovablePoints(currentPoint));

        return points;
    }

    private Optional<Point> addStartPositionMovablePoint(Point currentPoint) {
        if (!isStartPosition(currentPoint)) {
            return Optional.empty();
        }
        if (isWhite()) {
            return movablePoint(currentPoint, Direction.TWO_UPPER);
        }
        return movablePoint(currentPoint, Direction.TWO_LOWER);
    }

    private List<Point> addIfBlackPawnMovablePoints(Point currentPoint) {
        if (isWhite()) {
            return List.of();
        }
        return blackPawnDirections().stream().flatMap(direction -> movablePoint(currentPoint, direction).stream())
                .toList();
    }

    private List<Point> addIfWhitePawnMovablePoints(Point currentPoint) {
        if (isWhite()) {
            return whitePawnDirections().stream().flatMap(direction -> movablePoint(currentPoint, direction).stream())
                    .toList();
        }
        return List.of();
    }

    private Optional<Point> movablePoint(Point currentPoint, Direction direction) {
        if (currentPoint.addable(direction)) {
            Point point = currentPoint.move(direction);
            return Optional.of(point);
        }
        return Optional.empty();
    }

    private boolean isStartPosition(Point currentPoint) {
        return currentPoint.isSameRank(Rank.SECOND) || currentPoint.isSameRank(Rank.SEVENTH);
    }

    private List<Direction> whitePawnDirections() {
        return List.of(Direction.UPPER, Direction.UPPER_LEFT, Direction.UPPER_RIGHT);
    }

    private List<Direction> blackPawnDirections() {
        return List.of(Direction.LOWER, Direction.LOWER_LEFT, Direction.LOWER_RIGHT);
    }

    private boolean canAttack(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isSlopeOneDiagonal(nextPoint) && target != Piece.empty() && !this.isSameTeam(target);
    }

    private boolean canForward(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isStraight(nextPoint) && target == Piece.empty();
    }
}
