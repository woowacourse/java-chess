package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece {
    private static final Score score = new Score(9);

    public Queen(Team team) {
        super(team, Team.convertName("q", team));
    }

    @Override
    public Score calculateScore(PiecesState piecesState) {
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
