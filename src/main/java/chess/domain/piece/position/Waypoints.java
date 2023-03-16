package chess.domain.piece.position;

import java.util.List;

public class Waypoints {

    private final List<PiecePosition> wayPoints;

    private Waypoints(final List<PiecePosition> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public static Waypoints from(final List<PiecePosition> wayPoints) {
        return new Waypoints(wayPoints);
    }

    public List<PiecePosition> wayPoints() {
        return wayPoints;
    }
}
