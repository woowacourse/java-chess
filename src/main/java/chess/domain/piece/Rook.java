package chess.domain.piece;

import chess.domain.Position;

public class Rook implements Piece {

    private Position position;

    public Rook(Position position) {
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
}
