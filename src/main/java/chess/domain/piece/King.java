package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class King extends Piece {
    private static final double MAX_DISTANCE = Math.sqrt(2);
    private static final Score score = new Score(0);

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(Arrays.asList(
            new IsStayed(),
            new CanNotReach(MAX_DISTANCE),
            new IsAttackingSameTeam())
    );

    public King(Team team) {
        super(team, Team.convertName("k", team));
    }

    @Override
    public Score calculateScore(PiecesState piecesState) {
        return score;
    }

    @Override
    protected List<CanNotMoveStrategy> constituteStrategies(Position from) {
        return DEFAULT_CAN_NOT_MOVE_STRATEGIES;
    }
}
