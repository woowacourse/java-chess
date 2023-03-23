package chess.domain.piece.position;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final PiecePosition source;
    private final PiecePosition destination;

    public Path(final PiecePosition source, final PiecePosition destination) {
        validate(source, destination);
        this.source = source;
        this.destination = destination;
    }

    private void validate(final PiecePosition source, final PiecePosition destination) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("출발지와 목적지가 동일할 수 없습니다.");
        }
    }

    public int fileDistance() {
        return source.fileDistance(destination);
    }

    public int rankDistance() {
        return source.rankDistance(destination);
    }

    public boolean isDestinationRelativelySouth() {
        return source.rankDistance(destination) < 0;
    }

    public boolean isDestinationRelativelyNorth() {
        return source.rankDistance(destination) > 0;
    }

    public boolean isStraight() {
        return !(Math.abs(source.rankDistance(destination)) > 0
                && Math.abs(source.fileDistance(destination)) > 0);
    }

    public boolean isDiagonal() {
        return Math.abs(source.rankDistance(destination)) == Math.abs(source.fileDistance(destination));
    }

    public boolean isUnitDistance() {
        return Math.abs(source.rankDistance(destination)) <= 1
                && Math.abs(source.fileDistance(destination)) <= 1;
    }

    public List<PiecePosition> wayPoints() {
        final List<PiecePosition> wayPoints = new ArrayList<>();
        PiecePosition current = source;
        while (!current.equals(destination)) {
            current = current.move(current.direction(destination));
            wayPoints.add(current);
        }
        wayPoints.remove(destination);
        return wayPoints;
    }

    public List<PiecePosition> wayPointsWithDestination() {
        final List<PiecePosition> wayPoints = wayPoints();
        wayPoints.add(destination);
        return wayPoints;
    }

    public List<PiecePosition> destination() {
        return List.of(destination);
    }
}
