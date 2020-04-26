package chess.domain.piece.policy.move;

import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.List;

public class InitializedPawnCanNotMoveStrategy extends MultipleCanNotMoveStrategy{
    private static final int MAX_DISTANCE = 2;

    private final Team team;

    public InitializedPawnCanNotMoveStrategy(Team team) {
        this.team = team;
    }

    @Override
    protected List<CanNotMoveStrategy> constitueStrategies() {
        List<CanNotMoveStrategy> canNotMoveStrategies = Arrays.asList(
                new IsStayed(),
                new IsNotHeadingForward(),
                new CanNotReach(MAX_DISTANCE),
                new HasHindranceInTwoStep(team.getForwardDirection()),
                new IsAttackingSameTeam(),
                new PawnIsDiagonalWithoutAttack(),
                new PawnIsVerticalWithAttack()
        );
        return null;
    }
}
