package chess.domain.piece;

import chess.domain.Movement;
import java.util.List;

public abstract class Pawn extends LimitedMovingChessPiece {

    public Pawn(List<Movement> movements) {
        super(movements);
    }

    @Override
    public String name() {
        return "P";
    }
}
