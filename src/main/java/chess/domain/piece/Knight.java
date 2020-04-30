package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
    private static final double MAX_DISTANCE = Math.sqrt(Math.pow(2, 2) + 1);

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(Arrays.asList(
            new IsStayed(),
            new CanNotReach(MAX_DISTANCE),
            new IsHeadingStraightDirection(),
            new IsAttackingSameTeam())
    );

    public Knight(Team team) {
        super(team);
    }

    @Override
    protected List<CanNotMoveStrategy> constituteStrategies(Position from) {
        return DEFAULT_CAN_NOT_MOVE_STRATEGIES;
    }
}
