package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final double MAX_DISTANCE = Math.sqrt(Math.pow(2, 2) + 1);

    public Knight(Team team) {
        super(team);
    }

    @Override
    protected CanNotMoveStrategy constituteStrategy() {
        List<CanNotMoveStrategy> canNotMoveStrategies = Arrays.asList(
                new IsStayed(),
                new CanNotReach(MAX_DISTANCE),
                new IsHeadingStraightDirection(),
                new IsAttackingSameTeam()
        );
        return new MultipleCanNotMoveStrategy(canNotMoveStrategies);
    }
}
