package chess.piece;

import java.util.Objects;

import chess.Movement;
import chess.Position;

public class Queen {

    private final Position position;

    public Queen(final Position position) {
        this.position = position;
    }

    public Queen move(final Movement movement, final int moveCount) {
        if (!movement.isHorizontal() && !movement.isVertical() && !movement.isDiagonal()) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        Position movedPosition = new Position(position.row(), position.column());
        for (int count = 0; count < moveCount; count++) {
            if (!movedPosition.canMove(movement)) {
                throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
            }
            movedPosition = movedPosition.move(movement);
        }
        return new Queen(movedPosition);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Queen queen)) {
            return false;
        }
        return Objects.equals(position, queen.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

}
