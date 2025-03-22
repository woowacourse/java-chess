package chess.piece;

import chess.board.Movement;
import chess.board.Position;
import java.util.Set;

public class Queen implements Piece {

    private static final Set<Movement> DIRECTIONS = Set.of(
            Movement.UP,
            Movement.DOWN,
            Movement.RIGHT,
            Movement.LEFT,
            Movement.LEFT_DOWN,
            Movement.LEFT_UP,
            Movement.RIGHT_DOWN,
            Movement.RIGHT_UP
    );

    @Override
    public boolean moveToDestination(Position start, Position end) {
        for (Movement direction : DIRECTIONS) {
            Position current = new Position(start.row(), start.column());
            while (!current.equals(end) && current.canMove(direction)) {
                current = current.move(direction);
            }
            if(current.equals(end)) {
                return true;
            }
        }

        return false;
    }
}
