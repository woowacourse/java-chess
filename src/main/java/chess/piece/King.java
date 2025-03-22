package chess.piece;

import java.util.List;
import java.util.Objects;

import chess.Movement;
import chess.Position;

public class King {

    private static final List<Movement> MOVEMENTS = List.of(
            Movement.UP, Movement.LEFT, Movement.DOWN, Movement.RIGHT,
            Movement.LEFT_UP, Movement.RIGHT_UP, Movement.LEFT_DOWN, Movement.RIGHT_DOWN
    );

    private final Position position;

    public King(final Position position) {
        this.position = position;
    }

    public King move(final Movement movement) {
        if (MOVEMENTS.contains(movement)) {
            return new King(position.move(movement));
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof King king)) {
            return false;
        }
        return Objects.equals(position, king.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

}
