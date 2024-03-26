package chess.domain.piece;

import chess.domain.point.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Pawn extends MultiMovePiece {
    private static final int TWO_RANK = 2;

    public Pawn(Team team) {
        super(Type.PAWN, team);
    }

    @Override
    public boolean isMovableDirection(final Point departure, final Point destination) {
        Team team = getTeam();
        List<Point> movablePoints = findMovablePoints(departure, team);

        return movablePoints.contains(destination);
    }

    private List<Point> findMovablePoints(Point currentPoint, final Team team) {
        List<Point> points = new ArrayList<>();
        int forwardDirection = team.forwardDirection();

        if (currentPoint.isInitialPointOfPawn()) {
            findMovablePoint(points, currentPoint, 0, TWO_RANK * forwardDirection);
        }
        for (Direction direction : Direction.findPawnDirections()) {
            findMovablePoint(points, currentPoint, direction.file(), direction.rank() * forwardDirection);
        }
        return points;
    }

    private void findMovablePoint(List<Point> points, Point currentPoint, int addFile, int addRank) {
        if (currentPoint.addable(addFile, addRank)) {
            Point point = currentPoint.add(addFile, addRank);
            points.add(point);
        }
    }

    @Override
    public boolean isMovable(final Point departure, final Point destination, final Map<Point, Piece> board) {
        if (departure.isDiagonalWithSlopeOfOne(destination) && board.get(destination).equals(Empty.INSTANCE)) {
            return false;
        }
        if (departure.isStraight(destination) && !board.get(destination).equals(Empty.INSTANCE)) {
            return false;
        }
        return super.isMovable(departure, destination, board);
    }
}
