package chess.piece;

import static chess.Movement.DOWN_DOWN_LEFT;
import static chess.Movement.DOWN_DOWN_RIGHT;
import static chess.Movement.LEFT_LEFT_DOWN;
import static chess.Movement.LEFT_LEFT_UP;
import static chess.Movement.RIGHT_RIGHT_DOWN;
import static chess.Movement.RIGHT_RIGHT_UP;
import static chess.Movement.UP_UP_LEFT;
import static chess.Movement.UP_UP_RIGHT;

import chess.Movement;
import chess.Position;
import java.util.List;

public class Knight extends Piece{
    private final static List<Movement> knightMovement =
            List.of(LEFT_LEFT_UP,
                    LEFT_LEFT_DOWN,
                    RIGHT_RIGHT_UP,
                    RIGHT_RIGHT_DOWN,
                    DOWN_DOWN_LEFT,
                    DOWN_DOWN_RIGHT,
                    UP_UP_LEFT,
                    UP_UP_RIGHT
            );

    private final List<Movement> availableMovement;

    public Knight() {
        this.availableMovement = knightMovement;
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
}
