package chess.domain.piece.policy.move;

import chess.domain.piece.MovedPawn;

import java.util.Arrays;
import java.util.List;

public class MovedPawnCanNotMoveStrategy extends MultipleCanNotMoveStrategy {
    public static final double MAX_DISTANCE = Math.sqrt(2);

    @Override
    protected List<CanNotMoveStrategy> constitueStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new IsNotHeadingForward(),
                new CanNotReach(MAX_DISTANCE),
                new HasHindranceStraightInBetween(),
                new IsAttackingSameTeam(),
                new PawnIsDiagonalWithoutAttack(),
                new PawnIsVerticalWithAttack()
        );
    }
}
