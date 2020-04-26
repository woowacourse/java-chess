package chess.domain.piece.policy.move;

import javax.management.relation.RoleList;
import java.util.Arrays;
import java.util.List;

public class QueenCanNotMoveStrategy extends MultipleCanNotMoveStrategy {
    @Override
    protected List<CanNotMoveStrategy> constitueStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new HasHindranceStraightInBetween(),
                new IsAttackingSameTeam(),
                new IsNotHeadingStraightDirection()
        );
    }
}
