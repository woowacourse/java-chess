package chess.domain.piece;

import chess.domain.Movement;
import java.util.List;

public abstract class LimitedMovingChessPiece implements ChessPiece {

    protected final List<Movement> movements;

    public LimitedMovingChessPiece(List<Movement> movements) {
        this.movements = movements;
    }

    @Override
    public void move() {

    }
}
