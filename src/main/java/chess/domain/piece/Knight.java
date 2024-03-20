package chess.domain.piece;

import chess.domain.movement.KnightMovement;
import java.util.List;

public final class Knight extends Piece {

    public Knight(Team team) {
        super(team, List.of(new KnightMovement()));
    }
}
