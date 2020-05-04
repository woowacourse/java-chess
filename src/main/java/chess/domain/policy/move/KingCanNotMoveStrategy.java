package chess.domain.policy.move;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KingCanNotMoveStrategy extends MultipleAnyMatchCanNotMoveStrategies {
    private static final double MAX_DISTANCE = Math.sqrt(2);

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(Arrays.asList(
            new IsStayed(),
            new CanNotReach(MAX_DISTANCE),
            new IsAttackingSameTeam())
    );

    @Override
    protected List<CanNotMoveStrategy> constituteCanNotMoveStrategies(Position position) {
        return DEFAULT_CAN_NOT_MOVE_STRATEGIES;
    }
}
