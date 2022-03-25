package chess.domain.piece;

import chess.domain.Position;
import java.util.Objects;

public class Rook implements Piece {

    private final State state;

    private Position position;

    public Rook(Position position) {
        this.state = State.ROOK;
        this.position = position;
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition) {
        if (!currentPosition.isMoveLinear(destinationPosition)) {
            throw new IllegalArgumentException("룩은 상하좌우 중 한 방향으로만 이동해야 합니다.");
        }
        if (currentPosition.calculateDistance(destinationPosition) == 0) {
            throw new IllegalArgumentException("룩은 1칸 이상 이동해야 합니다.");
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
        Rook rook = (Rook) o;
        return Objects.equals(position, rook.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
