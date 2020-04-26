package chess.domain.piece.policy.move;

import java.util.Arrays;
import java.util.List;

public class KingCanNotMoveStrategy extends MultipleCanNotMoveStrategy {
    private static final double MAX_DISTANCE = Math.sqrt(2);

    @Override
    protected List<CanNotMoveStrategy> constitueStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new CanNotReach(MAX_DISTANCE),
                new IsAttackingSameTeam()
        );
    }
}
