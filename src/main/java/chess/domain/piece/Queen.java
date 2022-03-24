package chess.domain.piece;

import chess.domain.Position;

public class Queen implements Piece {

    private Position position;

    public Queen(Position position) {
        this.position = position;
    }

    @Override
    public Position move(final Position currentPosition, final Position destinationPosition) {
        final boolean isMoveLinear = currentPosition.isMoveLinear(destinationPosition);
        final boolean isMoveDiagonal = currentPosition.isMoveDiagonal(destinationPosition);

        if (!isMoveLinear && !isMoveDiagonal) {
            throw new IllegalArgumentException("퀸은 상하좌우, 대각선 중 한 방향으로 이동해야 합니다.");
        }
        return position = destinationPosition;
    }

    @Override
    public boolean exist(final Position checkingPosition) {
        return position.equals(checkingPosition);
    }
}
