package chess.domain.piece;

import chess.domain.Position;

public class Bishop {

    private Position position;

    public Bishop(Position position) {
        this.position = position;
    }

    public Position move(final Position currentPosition, final Position destinationPosition) {
        if (!currentPosition.isMoveDiagonal(destinationPosition)) {
            throw new IllegalArgumentException("비숍은 대각선으로 이동해야 합니다.");
        }
        if (currentPosition.calculateDistance(destinationPosition) == 0) {
            throw new IllegalArgumentException("비숍은 1칸 이상 이동해야 합니다.");
        }
        return position = destinationPosition;
    }
}
