package chess.domain.piece;

import chess.domain.movement.MovementRule;
import chess.domain.movement.continuous.NorthEastMovement;
import chess.domain.movement.continuous.NorthWestMovement;
import chess.domain.movement.continuous.SouthEastMovement;
import chess.domain.movement.continuous.SouthWestMovement;
import java.util.List;

public final class Bishop extends Piece {

    private static final List<MovementRule> MOVEMENT_RULES = List.of(
            new NorthEastMovement(), new SouthEastMovement(), new NorthWestMovement(), new SouthWestMovement());

    public Bishop(Team team) {
        super(team, MOVEMENT_RULES);
    }
}
