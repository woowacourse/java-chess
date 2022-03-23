package chess.domain;

public class Knight {

    private Position position;

    public Knight(Position position) {
        this.position = position;
    }

    public Position move(final Position currentPosition, final Position destinationPosition) {
        if (!currentPosition.isMoveOfKnight(destinationPosition)) {
            throw new IllegalArgumentException("나이트는 상하좌우로 1칸 이동 후 대각선으로 1칸 이동해야 합니다.");
        }
        return position = destinationPosition;
    }
}
