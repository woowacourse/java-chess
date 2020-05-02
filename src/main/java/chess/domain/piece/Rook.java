package chess.domain.piece;

import chess.domain.piece.policy.move.HasHindrancePerpendicularlyInBetween;
import chess.domain.piece.policy.move.IsAttackingSameTeam;
import chess.domain.piece.policy.move.IsHeadingDiagonalDirection;
import chess.domain.piece.policy.move.IsStayed;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {
    private static final Score score = Score.of(5);

    public Rook(Team team) {
        super(team, Team.convertName("r", team));
    }

    @Override
    public Score calculateScore(CalculateScoreStrategy calculateScoreStrategy) {
        return score;
    }

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(Arrays.asList(
            new IsStayed(),
            new HasHindrancePerpendicularlyInBetween(),
            new IsAttackingSameTeam(),
            new IsHeadingDiagonalDirection())
    );

    @Override
    protected List<CanNotMoveStrategy> constituteStrategies(Position from) {
        return DEFAULT_CAN_NOT_MOVE_STRATEGIES;
    }
}
