package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.List;

public class InitializedPawn extends Piece {
    private static final int MAX_DISTANCE = 2;

    public InitializedPawn(Team team) {
        super(team);
    }

    @Override
    protected CanNotMoveStrategy constituteStrategy() {
        List<CanNotMoveStrategy> canNotMoveStrategies = Arrays.asList(
                new IsStayed(),
                new CanNotReach(MAX_DISTANCE),
                new HasHindranceInTwoStep(team.getForwardDirection()),
                new IsAttackingSameTeam(),
                new PawnIsDiagonalWithoutAttack(),
                new PawnIsVerticalWithAttack()
        );

        return new MultipleCanNotMoveStrategy(canNotMoveStrategies);
    }
}
