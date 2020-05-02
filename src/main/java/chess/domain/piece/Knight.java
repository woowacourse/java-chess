package chess.domain.piece;

import chess.domain.piece.policy.move.CanNotReach;
import chess.domain.piece.policy.move.IsAttackingSameTeam;
import chess.domain.piece.policy.move.IsHeadingStraightDirection;
import chess.domain.piece.policy.move.IsStayed;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
    private static final double MAX_DISTANCE = Math.sqrt(Math.pow(2, 2) + 1);
    private static final Score score = Score.of(2.5);

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(Arrays.asList(
            new IsStayed(),
            new CanNotReach(MAX_DISTANCE),
            new IsHeadingStraightDirection(),
            new IsAttackingSameTeam())
    );

    public Knight(Team team) {
        super(team, Team.convertName("n", team));
    }

    @Override
    public Score calculateScore(CalculateScoreStrategy calculateScoreStrategy) {
        return score;
    }

    @Override
    protected List<CanNotMoveStrategy> constituteStrategies(Position from) {
        return DEFAULT_CAN_NOT_MOVE_STRATEGIES;
    }
}
