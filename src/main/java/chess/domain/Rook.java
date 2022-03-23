package chess.domain;

public class Rook {

    private Position position;

    public Rook(Position position) {
        this.position = position;
    }

    public Position move(final Position currentPosition, final Position destinationPosition) {
        if (!currentPosition.isMoveLinear(destinationPosition)) {
            throw new IllegalArgumentException("룩은 상하좌우 중 한 방향으로만 이동해야 합니다.");
        }
        if (currentPosition.calculateDistance(destinationPosition) == 0) {
            throw new IllegalArgumentException("룩은 1칸 이상 이동해야 합니다.");
        }
        return position = destinationPosition;
    }
}
