package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.position.PawnInitialRow;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    private static final int INITIAL_MAX_DISTANCE = 2;
    private static final int DEFAULT_MAX_DISTANCE = 1;

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = new ArrayList<>(
            Arrays.asList(
                    new IsStayed(),
                    new HasHindrancePerpendicularlyInBetween(),
                    new IsAttackingSameTeam(),
                    new IsDiagonalWithoutAttack(),
                    new IsVerticalWithAttack()
            )
    );

    public Pawn(Team team) {
        super(team);
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
