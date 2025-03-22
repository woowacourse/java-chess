package chess.piece;

import chess.Movement;
import chess.Position;
import java.util.List;

public class King extends Piece{
    private final static List<Movement> kingMovement =
            List.of(Movement.UP,
                    Movement.DOWN,
                    Movement.LEFT,
                    Movement.RIGHT,
                    Movement.LEFT_UP,
                    Movement.RIGHT_UP,
                    Movement.LEFT_DOWN,
                    Movement.RIGHT_DOWN);

    private final List<Movement> availableMovement;

    public King() {
        this.availableMovement = kingMovement;
    }

    public boolean canMove(Position start, Position target) {
        for (Movement movement : availableMovement) {
            if(!start.canMove(movement)) {
                continue;
            }

            Position moved = start.move(movement);
            if(moved.equals(target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        return List.of(target);
    }
}
