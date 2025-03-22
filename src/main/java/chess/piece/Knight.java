package chess.piece;

import chess.Movement;
import chess.Position;
import java.util.Set;

public class Knight {
    private Set<Movement> movements = Set.of(
            Movement.UP_UP_LEFT,
            Movement.UP_UP_RIGHT,
            Movement.RIGHT_RIGHT_UP,
            Movement.RIGHT_RIGHT_DOWN,
            Movement.LEFT_LEFT_DOWN,
            Movement.LEFT_LEFT_UP,
            Movement.DOWN_DOWN_LEFT,
            Movement.DOWN_DOWN_RIGHT);

    public Set<Position> canMove(Position from, Position to) {
        Set<Position> positions = from.findMoveAblePositions(movements);

        if (!positions.contains(to)) {
            throw new IllegalArgumentException("애초에 니 못감 ㅅㄱㅇ");
        }
        return Set.of();
    }
}
