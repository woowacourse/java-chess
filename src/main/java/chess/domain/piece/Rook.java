package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.MovementRule;
import chess.domain.movement.continuous.EastMovement;
import chess.domain.movement.continuous.NorthMovement;
import chess.domain.movement.continuous.SouthMovement;
import chess.domain.movement.continuous.WestMovement;
import java.util.List;

public final class Rook extends Piece {

    private static final List<MovementRule> MOVEMENT_RULES = List.of(
            new EastMovement(), new WestMovement(), new SouthMovement(), new NorthMovement());

    public Rook(Team team) {
        super(team, MOVEMENT_RULES);
    }
}
