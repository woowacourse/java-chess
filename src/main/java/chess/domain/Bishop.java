package chess.domain;

public class Bishop {

    private static final int NOT_MOVE_COUNT = 0;

    private Position position;

    public Bishop(Position position) {
        this.position = position;
    }

    public Position move(final Position currentPosition, final Position destinationPosition) {
        final int count = currentPosition.countMoveDiagonal(destinationPosition);
        if (count == NOT_MOVE_COUNT) {
            throw new IllegalArgumentException("비숍은 1칸 이상 이동해야 합니다.");
        }
        return position = destinationPosition;
    }
}
