package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.position.PawnInitialRow;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    private static final int INITIAL_MAX_DISTANCE = 2;
    private static final int DEFAULT_MAX_DISTANCE = 1;
    private static final Score SCORE_WHEN_HAS_NOT_PEER = Score.of(1);
    private static final Score SCORE_WHEN_HAS_PEER = Score.of(0.5);

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(
            Arrays.asList(
                    new IsStayed(),
                    new HasHindrancePerpendicularlyInBetween(),
                    new IsAttackingSameTeam(),
                    new IsDiagonalWithoutAttack(),
                    new IsVerticalWithAttack()
            )
    );

    public Pawn(Team team) {
        super(team, Team.convertName("p", team));
    }

    @Override
    public Score calculateScore(CalculateScoreStrategy calculateScoreStrategy) {
//        if (file.hasMultiplePawn(team)) {
//            return SCORE_WHEN_HAS_PEER;
//        }

        return SCORE_WHEN_HAS_NOT_PEER;
    }

    @Override
    protected List<CanNotMoveStrategy> constituteStrategies(Position from) {
        if (isInitialized(from)) {
            return concludeStrategy(INITIAL_MAX_DISTANCE);
        }
        return concludeStrategy(DEFAULT_MAX_DISTANCE);
    }

    private boolean isInitialized(Position position) {
        PawnInitialRow initialRow = PawnInitialRow.valueOf(team);
        return initialRow.match(position);
    }

    private List<CanNotMoveStrategy> concludeStrategy(int maxDistance) {
        List<CanNotMoveStrategy> canNotMoveStrategies = new ArrayList<>(DEFAULT_CAN_NOT_MOVE_STRATEGIES);
        canNotMoveStrategies.add(new CanNotReach(maxDistance));
        return Collections.unmodifiableList(canNotMoveStrategies);
    }
}
