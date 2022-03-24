package chess.domain.piece;

import chess.domain.Position;
import java.util.Objects;

public class King implements Piece {

    private static final int KING_LINEAR_MOVE_DISTANCE = 1;
    private static final int KING_DIAGONAL_MOVE_DISTANCE = 2;

    private Position position;

    public King(Position position) {
        this.position = position;
    }

    @Override
    public Position move(Position currentPosition, Position destinationPosition) {
        boolean isMoveLinear = currentPosition.isMoveLinear(destinationPosition);
        boolean isMoveDiagonal = currentPosition.isMoveDiagonal(destinationPosition);

        if (!isMoveLinear && !isMoveDiagonal) {
            throw new IllegalArgumentException("킹은 상하좌우, 대각선 중 한 방향으로 이동해야 합니다.");
        }

        validateMoveDistance(currentPosition.calculateDistance(destinationPosition), isMoveLinear, isMoveDiagonal);

        return position = destinationPosition;
    }

    private void validateMoveDistance(final int distance, final boolean isMoveLinear, final boolean isMoveDiagonal) {
        if (isMoveLinear && distance != KING_LINEAR_MOVE_DISTANCE
                || isMoveDiagonal && distance != KING_DIAGONAL_MOVE_DISTANCE) {
            throw new IllegalArgumentException("킹은 1칸만 이동할 수 있습니다.");
        }
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
        King king = (King) o;
        return Objects.equals(position, king.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
