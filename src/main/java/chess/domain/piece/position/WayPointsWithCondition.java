package chess.domain.piece.position;

import java.util.Collections;
import java.util.List;

public class WayPointsWithCondition {

    private final List<PiecePosition> wayPoints;
    private final Condition condition;

    private WayPointsWithCondition(final List<PiecePosition> wayPoints, final Condition condition) {
        this.wayPoints = wayPoints;
        this.condition = condition;
    }

    public static WayPointsWithCondition possible(final List<PiecePosition> wayPoints) {
        return new WayPointsWithCondition(wayPoints, Condition.POSSIBLE);
    }

    public static WayPointsWithCondition onlyEnemy() {
        return new WayPointsWithCondition(Collections.emptyList(), Condition.ONLY_DESTINATION_ENEMY);
    }

    public List<PiecePosition> wayPoints() {
        return wayPoints;
    }

    public Condition condition() {
        return condition;
    }
}
