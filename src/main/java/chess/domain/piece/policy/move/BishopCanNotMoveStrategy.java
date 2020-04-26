package chess.domain.piece.policy.move;

import java.util.Arrays;
import java.util.List;

public class BishopCanNotMoveStrategy extends MultipleCanNotMoveStrategy {
    @Override
    protected List<CanNotMoveStrategy> constitueStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new HasHindranceDiagonallyInBetween(),
                new IsAttackingSameTeam(),
                new IsNotHeadingStraightDiagonalDirection()
        );
    }
}
