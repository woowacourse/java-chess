package chess.domain.policy.move;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BishopCanNotMoveStrategy extends MultipleAnyMatchCanNotMoveStrategies {
    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(Arrays.asList(
            new IsStayed(),
            new HasHindranceStraightDiagonallyInBetween(),
            new IsAttackingSameTeam(),
            new IsNotHeadingStraightDiagonalDirection())
    );

    @Override
    protected List<CanNotMoveStrategy> constituteCanNotMoveStrategies(Position position) {
        return DEFAULT_CAN_NOT_MOVE_STRATEGIES;
    }
}
