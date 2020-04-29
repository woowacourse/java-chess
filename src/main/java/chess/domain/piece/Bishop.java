package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Team team) {
        super(team);
    }

    @Override
    protected CanNotMoveStrategy constituteStrategy() {
        List<CanNotMoveStrategy> canNotMoveStrategies = Arrays.asList(
                new IsStayed(),
                new HasHindranceDiagonallyInBetween(),
                new IsAttackingSameTeam(),
                new IsNotHeadingStraightDiagonalDirection()
        );
        return new MultipleCanNotMoveStrategy(canNotMoveStrategies);
    }
}
