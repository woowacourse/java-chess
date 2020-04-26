package chess.domain.piece.policy.move;

import java.util.Arrays;
import java.util.List;

public class KnightCanNotMoveStrategy extends MultipleCanNotMoveStrategy {
    private static final double MAX_DISTANCE = Math.sqrt(Math.pow(2, 2) + 1);

    @Override
    protected List<CanNotMoveStrategy> constitueStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new CanNotReach(MAX_DISTANCE),
                new IsHeadingStraightDirection(),
                new IsAttackingSameTeam()
        );
    }
}
