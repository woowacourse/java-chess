package chess.domain.piece;

import chess.domain.movement.EastMovement;
import chess.domain.movement.MovementRule;
import chess.domain.movement.NorthEastMovement;
import chess.domain.movement.NorthMovement;
import chess.domain.movement.NorthWestMovement;
import chess.domain.movement.SouthEastMovement;
import chess.domain.movement.SouthWestMovement;
import chess.domain.movement.WestMovement;
import java.util.List;

public final class Queen extends chess.domain.piece.Piece {

    private static final List<MovementRule> MOVEMENT_RULES = List.of(
            new EastMovement(), new WestMovement(), new SouthEastMovement(), new NorthMovement(),
            new NorthEastMovement(), new SouthEastMovement(), new NorthWestMovement(), new SouthWestMovement());

    public Queen(Team team) {
        super(team, MOVEMENT_RULES);
    }
}
