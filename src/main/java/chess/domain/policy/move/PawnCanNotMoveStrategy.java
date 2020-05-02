package chess.domain.policy.move;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.team.Team;
import chess.domain.position.PawnInitialRow;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PawnCanNotMoveStrategy extends MultipleAnyMatchCanNotMoveStrategies {
    private static final int INITIAL_MAX_DISTANCE = 2;
    private static final int MOVED_MAX_DISTANCE = 1;
    private static final List<CanNotMoveStrategy> DEFAULT_PAWN_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(
            Arrays.asList(
                    new IsStayed(),
                    new HasHindrancePerpendicularlyInBetween(),
                    new IsAttackingSameTeam(),
                    new IsDiagonalWithoutAttack(),
                    new IsVerticalWithAttack()
            )
    );

    private final Team team;

    public PawnCanNotMoveStrategy(Team team) {
        this.team = team;
    }


    @Override
    protected List<CanNotMoveStrategy> constituteCanNotMoveStrategies(Position position) {
        if (isPawnInitialized(position)) {
            return concludeCanNotMoveStrategies(INITIAL_MAX_DISTANCE);
        }
        return concludeCanNotMoveStrategies(MOVED_MAX_DISTANCE);
    }

    private boolean isPawnInitialized(Position position) {
        PawnInitialRow initialRow = PawnInitialRow.valueOf(team);
        return initialRow.match(position);
    }

    private List<CanNotMoveStrategy> concludeCanNotMoveStrategies(int maxDistance) {
        List<CanNotMoveStrategy> canNotMoveStrategies = new ArrayList<>(DEFAULT_PAWN_CAN_NOT_MOVE_STRATEGIES);
        canNotMoveStrategies.add(new CanNotReach(maxDistance));
        canNotMoveStrategies = Collections.unmodifiableList(canNotMoveStrategies);
        return canNotMoveStrategies;
    }
}
