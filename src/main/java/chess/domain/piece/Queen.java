package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {
    public Queen(Team team) {
        super(team);
    }

    @Override
    protected CanNotMoveStrategy constituteStrategy() {
        List<CanNotMoveStrategy> canNotMoveStrategies = Arrays.asList(
                new IsStayed(),
                new HasHindranceStraightInBetween(),
                new IsAttackingSameTeam(),
                new IsNotHeadingStraightDirection()
        );
        return new MultipleCanNotMoveStrategy(canNotMoveStrategies);
    }
}
