package chess.domain.piece.position;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final PiecePosition source;
    private final PiecePosition destination;

    private Path(final PiecePosition source, final PiecePosition destination) {
        validate(source, destination);
        this.source = source;
        this.destination = destination;
    }

    private void validate(final PiecePosition source, final PiecePosition destination) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("출발지와 목적지가 동일할 수 없습니다.");
        }
    }

    public static Path of(final PiecePosition source, final PiecePosition destination) {
        return new Path(source, destination);
    }

    public int fileInterval() {
        return source.fileInterval(destination);
    }

    public int rankInterval() {
        return source.rankInterval(destination);
    }

    public boolean isDestinationRelativelySouth() {
        return source.rankInterval(destination) < 0;
    }

    public boolean isDestinationRelativelyNorth() {
        return source.rankInterval(destination) > 0;
    }

    public boolean isStraight() {
        return !(Math.abs(source.rankInterval(destination)) > 0
                && Math.abs(source.fileInterval(destination)) > 0);
    }

    public boolean isDiagonal() {
        return Math.abs(source.rankInterval(destination)) == Math.abs(source.fileInterval(destination));
    }

    public boolean isUnitDistance() {
        return Math.abs(source.rankInterval(destination)) <= 1
                && Math.abs(source.fileInterval(destination)) <= 1;
    }

    public boolean isTwoVerticalMove() {
        if (Math.abs(rankInterval()) != 2) {
            return false;
        }
        return Math.abs(fileInterval()) == 0;
    }

    public boolean isHorizontal() {
        return rankInterval() == 0;
    }

    public List<PiecePosition> waypoints() {
        final List<PiecePosition> waypoints = new ArrayList<>();
        PiecePosition current = source;
        while (!current.equals(destination)) {
            current = current.move(current.direction(destination));
            waypoints.add(current);
        }
        waypoints.remove(destination);
        return waypoints;
    }

    public PiecePosition source() {
        return source;
    }
}
