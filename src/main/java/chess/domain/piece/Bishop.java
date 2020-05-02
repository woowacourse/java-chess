package chess.domain.piece;

import chess.domain.piece.policy.move.HasHindranceStraightDiagonallyInBetween;
import chess.domain.piece.policy.move.IsAttackingSameTeam;
import chess.domain.piece.policy.move.IsNotHeadingStraightDiagonalDirection;
import chess.domain.piece.policy.move.IsStayed;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bishop extends Piece {
    private static final Score score = Score.of(3);

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(Arrays.asList(
            new IsStayed(),
            new HasHindranceStraightDiagonallyInBetween(),
            new IsAttackingSameTeam(),
            new IsNotHeadingStraightDiagonalDirection())
    );

    public Bishop(Team team) {
        super(team, Team.convertName("b", team));
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
