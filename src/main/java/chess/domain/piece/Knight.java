package chess.domain.piece;

import chess.domain.movement.MovementRule;
import chess.domain.movement.discrete.KnightMovement;
import java.util.List;

public final class Knight extends Piece {

    private static final List<MovementRule> MOVEMENT_RULES = List.of(new KnightMovement());

    public Knight(Team team) {
        super(team, MOVEMENT_RULES);
    }
}
