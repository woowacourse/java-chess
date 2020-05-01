package chess.domain.piece;

import chess.domain.piece.policy.move.HasHindrancePerpendicularlyInBetween;
import chess.domain.piece.policy.move.IsAttackingSameTeam;
import chess.domain.piece.policy.move.IsHeadingDiagonalDirection;
import chess.domain.piece.policy.move.IsStayed;
import chess.domain.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.Pieces;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {
    private static final Score score = new Score(5);

    public Rook(Team team) {
        super(team, Team.convertName("r", team));
    }

    @Override
    public Score calculateScore(Pieces pieces) {
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
