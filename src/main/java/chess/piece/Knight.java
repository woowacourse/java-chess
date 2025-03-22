package chess.piece;

import static chess.Movement.DOWN_DOWN_LEFT;
import static chess.Movement.DOWN_DOWN_RIGHT;
import static chess.Movement.LEFT_LEFT_DOWN;
import static chess.Movement.LEFT_LEFT_UP;
import static chess.Movement.RIGHT_RIGHT_DOWN;
import static chess.Movement.RIGHT_RIGHT_UP;
import static chess.Movement.UP_UP_LEFT;
import static chess.Movement.UP_UP_RIGHT;

import java.util.List;
import java.util.Objects;

import chess.Movement;
import chess.Position;

public class Knight {

    private static final List<Movement> MOVEMENTS = List.of(
            LEFT_LEFT_UP, LEFT_LEFT_DOWN, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN,
            UP_UP_LEFT, UP_UP_RIGHT, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT
    );

    private final Position position;

    public Knight(final Position position) {
        this.position = position;
    }

    public Knight move(final Movement movement) {
        if (!MOVEMENTS.contains(movement)) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        if (canMove(movement)) {
            return new Knight(position.move(movement));
        }
        throw new IllegalArgumentException("움직일 수 없습니다.");
    }

    private boolean canMove(final Movement movement) {
        return position.canMoveHorizontal(movement.x()) && position.canMoveVertical(movement.y());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knight knight)) {
            return false;
        }
        return Objects.equals(position, knight.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

}
