package chess.domain.piece;

import chess.domain.movement.EastMovement;
import chess.domain.movement.MovementRule;
import chess.domain.movement.NorthMovement;
import chess.domain.movement.SouthEastMovement;
import chess.domain.movement.WestMovement;
import java.util.List;

public final class Rook extends chess.domain.piece.Piece {

    private static final List<MovementRule> MOVEMENT_RULES = List.of(
            new EastMovement(), new WestMovement(), new SouthEastMovement(), new NorthMovement());

    public Rook(Team team) {
        super(team, MOVEMENT_RULES);
    }
}
