package chess.domain.piece.position;

import java.util.ArrayList;
import java.util.List;

public class WayPoints {

    private final List<PiecePosition> wayPoints;

    public WayPoints(final List<PiecePosition> wayPoints) {
        this.wayPoints = new ArrayList<>(wayPoints);
    }

    public List<PiecePosition> wayPoints() {
        return wayPoints;
    }
}
