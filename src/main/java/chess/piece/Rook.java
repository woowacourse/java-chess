package chess.piece;

import java.util.List;
import java.util.Objects;

import chess.Movement;
import chess.Position;

public class Rook {

    private static final List<Movement> MOVEMENTS = List.of(
            Movement.UP, Movement.LEFT, Movement.DOWN, Movement.RIGHT
    );

    private final Position position;

    public Rook(final Position position) {
        this.position = position;
    }

    public Rook move(final Movement movement, final int moveCount) {
        if (!MOVEMENTS.contains(movement)) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        Position movedPosition = new Position(position.row(), position.column());
        for (int count = 0; count < moveCount; count++) {
            if (!movedPosition.canMove(movement)) {
                throw new IllegalArgumentException("움직일 수 없습니다.");
            }
            movedPosition = movedPosition.move(movement);
        }
        return new Rook(movedPosition);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rook rook)) {
            return false;
        }
        return Objects.equals(position, rook.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

}
