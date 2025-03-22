package chess.piece;

import java.util.Objects;

import chess.Movement;
import chess.Position;

public class King {

    private final Position position;

    public King(final Position position) {
        this.position = position;
    }

    public King move(final Movement movement) {
        if (!movement.isHorizontal() && !movement.isVertical() && !movement.isDiagonal()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        return new King(position.move(movement));
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
