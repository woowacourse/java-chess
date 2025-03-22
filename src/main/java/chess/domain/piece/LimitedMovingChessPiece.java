package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import java.util.List;

public abstract class LimitedMovingChessPiece implements ChessPiece {

    protected final List<Movement> movements;

    public LimitedMovingChessPiece(List<Movement> movements) {
        this.movements = movements;
    }

    @Override
    public boolean canMove(Position origin, Position destination) {
        for (Movement movement : movements) {
            if (origin.move(movement).equals(destination)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void move() {

    }
}
