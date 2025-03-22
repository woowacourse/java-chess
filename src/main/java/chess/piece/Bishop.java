package chess.piece;

import java.util.Objects;

import chess.Movement;
import chess.Position;

public class Bishop {

    private final Position position;

    public Bishop(final Position position) {
        this.position = position;
    }

    public Bishop move(final Movement movement, final int moveCount) {
        if (!movement.isDiagonal()) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        Position movedPosition = new Position(position.row(), position.column());
        for (int count = 0; count < moveCount; count++) {
            if (!position.canMove(movement)) {
                throw new IllegalArgumentException("움직일 수 없습니다.");
            }
            try {
                movedPosition = movedPosition.move(movement);
            } catch (IllegalStateException e) {
                throw new IllegalArgumentException("움직일 수 없습니다.");
            }
        }
        return new Bishop(movedPosition);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bishop bishop)) {
            return false;
        }
        return Objects.equals(position, bishop.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

}
