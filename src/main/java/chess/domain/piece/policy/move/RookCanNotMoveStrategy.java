package chess.domain.piece.policy.move;

import java.util.Arrays;
import java.util.List;

public class RookCanNotMoveStrategy extends MultipleCanNotMoveStrategy {
    @Override
    protected List<CanNotMoveStrategy> constitueStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new HasHindrancePerpendicularlyInBetween(),
                new IsAttackingSameTeam(),
                new IsHeadingDiagonalDirection()
        );
    }
}
