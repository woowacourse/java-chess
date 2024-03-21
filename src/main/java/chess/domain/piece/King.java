package chess.domain.piece;

import chess.domain.movement.discrete.KingMovement;
import java.util.List;

public final class King extends Piece {

    public King(Team team) {
        super(team, List.of(new KingMovement()));
    }
}
