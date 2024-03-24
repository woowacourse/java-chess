package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.MovementRule;
import chess.domain.movement.discrete.KingMovement;
import java.util.List;

public final class King extends Piece {

    private static final List<MovementRule> MOVEMENT_RULES = List.of(new KingMovement());

    public King(Team team) {
        super(team, MOVEMENT_RULES);
    }
}
