package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import java.util.List;

public abstract class LinearMovingChessPiece implements ChessPiece {

    protected final List<Movement> directions;

    public LinearMovingChessPiece(List<Movement> directions) {
        this.directions = directions;
    }

    @Override
    public boolean canMove(Position origin, Position destination) {
        for (Movement direction : directions) {
            Position origin2 = origin;
            while (origin2.canMove(direction)) {
                origin2 = origin2.move(direction);
                if (origin2.equals(destination)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void move() {

    }
}
