package chess.domain.piece;

import chess.domain.Position;
import java.util.Objects;

public class Bishop implements Piece {

    private Position position;

    public Bishop(Position position) {
        this.position = position;
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition) {
        if (!currentPosition.isMoveDiagonal(destinationPosition)) {
            throw new IllegalArgumentException("비숍은 대각선으로 이동해야 합니다.");
        }
        if (currentPosition.calculateDistance(destinationPosition) == 0) {
            throw new IllegalArgumentException("비숍은 1칸 이상 이동해야 합니다.");
        }
        return position = destinationPosition;
    }

    @Override
    public boolean exist(final Position checkingPosition) {
        return position.equals(checkingPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bishop bishop = (Bishop) o;
        return Objects.equals(position, bishop.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
