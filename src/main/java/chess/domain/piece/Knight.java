package chess.domain.piece;

import static chess.domain.Movement.DOWN_DOWN_LEFT;
import static chess.domain.Movement.DOWN_DOWN_RIGHT;
import static chess.domain.Movement.LEFT_LEFT_DOWN;
import static chess.domain.Movement.LEFT_LEFT_UP;
import static chess.domain.Movement.RIGHT_RIGHT_DOWN;
import static chess.domain.Movement.RIGHT_RIGHT_UP;
import static chess.domain.Movement.UP_UP_LEFT;
import static chess.domain.Movement.UP_UP_RIGHT;

import chess.domain.Movement;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private final static List<Movement> canMove = List.of(
            UP_UP_LEFT,
            UP_UP_RIGHT,
            LEFT_LEFT_UP,
            LEFT_LEFT_DOWN,
            DOWN_DOWN_LEFT,
            DOWN_DOWN_RIGHT,
            RIGHT_RIGHT_UP,
            RIGHT_RIGHT_DOWN
    );

    public Knight(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public List<Position> canMove(final Position endPosition) {
        List<Position> positions = new ArrayList<>();
        for (Movement movement : canMove) {
            positions.clear();
            if (this.position.canMove(movement)) {
                Position nextPosition = this.position.move(movement);
                positions.add(nextPosition);
                if (nextPosition.equals(endPosition)) {
                    return positions;
                }
            }
        }
        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }
}
