package chess.domain.piece.position;

import java.util.Collections;
import java.util.List;

public class WayPointsWithCondition {

    private final List<PiecePosition> path;
    private final Condition condition;

    private WayPointsWithCondition(final List<PiecePosition> path, final Condition condition) {
        this.path = path;
        this.condition = condition;
    }

    public static WayPointsWithCondition impossible() {
        return new WayPointsWithCondition(Collections.emptyList(), Condition.IMPOSSIBLE);
    }

    public static WayPointsWithCondition possible(final List<PiecePosition> path) {
        return new WayPointsWithCondition(path, Condition.POSSIBLE);
    }

    public static WayPointsWithCondition onlyEnemy() {
        return new WayPointsWithCondition(Collections.emptyList(), Condition.ONLY_DESTINATION_ENEMY);
    }

    public List<PiecePosition> path() {
        return path;
    }

    public Condition condition() {
        return condition;
    }
}
