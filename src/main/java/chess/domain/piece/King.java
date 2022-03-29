package chess.domain.piece;

import chess.domain.Position;
import chess.domain.player.Team;

public class King extends Piece {

    private static final int KING_LINEAR_MOVE_DISTANCE = 1;
    private static final int KING_DIAGONAL_MOVE_DISTANCE = 2;

    public King(Position position) {
        super(State.KING, position);
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition, final Team team) {
        boolean isMoveLinear = currentPosition.isMoveLinear(destinationPosition);
        boolean isMoveDiagonal = currentPosition.isMoveDiagonal(destinationPosition);
        if (!isMoveLinear && !isMoveDiagonal) {
            throw new IllegalArgumentException("킹은 상하좌우, 대각선 중 한 방향으로 이동해야 합니다.");
        }
        validateMoveDistance(currentPosition.calculateDistance(destinationPosition), isMoveLinear, isMoveDiagonal);
        this.position = destinationPosition;
        return position;
    }

    private void validateMoveDistance(final int distance, final boolean isMoveLinear, final boolean isMoveDiagonal) {
        if (isMoveLinear && distance != KING_LINEAR_MOVE_DISTANCE
                || isMoveDiagonal && distance != KING_DIAGONAL_MOVE_DISTANCE) {
            throw new IllegalArgumentException("킹은 1칸만 이동할 수 있습니다.");
        }
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
