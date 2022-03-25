package chess.domain.piece;

import chess.domain.Position;
import java.util.Objects;

public class Knight implements Piece {

    private final State state;

    private Position position;

    public Knight(Position position) {
        this.state = State.KNIGHT;
        this.position = position;
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition) {
        if (!currentPosition.isMoveOfKnight(destinationPosition)) {
            throw new IllegalArgumentException("나이트는 상하좌우로 1칸 이동 후 대각선으로 1칸 이동해야 합니다.");
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
        Knight knight = (Knight) o;
        return Objects.equals(position, knight.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
