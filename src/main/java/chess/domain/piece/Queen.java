package chess.domain.piece;

import chess.domain.movement.MovementRule;
import chess.domain.movement.continuous.EastMovement;
import chess.domain.movement.continuous.NorthEastMovement;
import chess.domain.movement.continuous.NorthMovement;
import chess.domain.movement.continuous.NorthWestMovement;
import chess.domain.movement.continuous.SouthEastMovement;
import chess.domain.movement.continuous.SouthMovement;
import chess.domain.movement.continuous.SouthWestMovement;
import chess.domain.movement.continuous.WestMovement;
import java.util.List;

public final class Queen extends Piece {

    private static final List<MovementRule> MOVEMENT_RULES = List.of(
            new EastMovement(), new WestMovement(), new SouthMovement(), new NorthMovement(),
            new NorthEastMovement(), new SouthEastMovement(), new NorthWestMovement(), new SouthWestMovement());

    public Queen(Team team) {
        super(team, MOVEMENT_RULES);
    }
}
