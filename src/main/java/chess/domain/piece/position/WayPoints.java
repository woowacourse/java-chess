package chess.domain.piece.position;

import java.util.List;

public class WayPoints {

    private final List<PiecePosition> wayPoints;

    private WayPoints(final List<PiecePosition> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public static WayPoints from(final List<PiecePosition> wayPoints) {
        return new WayPoints(wayPoints);
    }

    public List<PiecePosition> wayPoints() {
        return wayPoints;
    }
}
