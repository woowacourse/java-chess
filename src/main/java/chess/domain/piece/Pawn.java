package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import java.util.List;

public abstract class Pawn extends LimitedMovingChessPiece {

    public Pawn(List<Movement> movements, Color side) {
        super(movements, side);
    }

    @Override
    public String name() {
        return "P";
    }
}
