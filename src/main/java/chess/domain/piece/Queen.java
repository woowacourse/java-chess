package chess.domain.piece;

import chess.domain.piece.policy.move.HasHindranceStraightInBetween;
import chess.domain.piece.policy.move.IsAttackingSameTeam;
import chess.domain.piece.policy.move.IsNotHeadingStraightDirection;
import chess.domain.piece.policy.move.IsStayed;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.File;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece {
    private static final Score score = Score.of(9);

    public Queen(Team team) {
        super(team, Team.convertName("q", team));
    }

    @Override
    public Score calculateScore(CalculateScoreStrategy calculateScoreStrategy) {
        return score;
    }

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(Arrays.asList(
            new IsStayed(),
            new HasHindranceStraightInBetween(),
            new IsAttackingSameTeam(),
            new IsNotHeadingStraightDirection())
    );

    @Override
    protected List<CanNotMoveStrategy> constituteStrategies(Position from) {
        return DEFAULT_CAN_NOT_MOVE_STRATEGIES;
    }
}
