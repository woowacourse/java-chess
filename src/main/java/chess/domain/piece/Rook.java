package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    public Rook(Team team) {
        super(team);
    }

    @Override
    protected CanNotMoveStrategy constituteStrategy() {
        List<CanNotMoveStrategy> canNotMoveStrategies = Arrays.asList(
                new IsStayed(),
                new HasHindrancePerpendicularlyInBetween(),
                new IsAttackingSameTeam(),
                new IsHeadingDiagonalDirection()
        );
        return new MultipleCanNotMoveStrategy(canNotMoveStrategies);
    }
}
