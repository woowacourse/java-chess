package chess.domain;

public class Pawn {

    private static final int PAWN_DEFAULT_MOVE_COUNT = 1;
    private static final int PAWN_FIRST_MOVE_COUNT = 2;

    private Position position;

    public Pawn(Position position) {
        this.position = position;
    }

    public Position move(final Position currentPosition, final Position destinationPosition) {
        if (this.position != currentPosition) {
            throw new IllegalArgumentException("현재 위치가 올바르지 않습니다.");
        }
        if (position.isFirstTurnOfPawn()) {
            validateFirstTurnMove(currentPosition, destinationPosition);
        }
        validateDefaultMoveCount(currentPosition.countMoveForward(destinationPosition));
        return position = destinationPosition;
    }

    private void validateDefaultMoveCount(final int count) {
        if (count != PAWN_DEFAULT_MOVE_COUNT) {
            throw new IllegalArgumentException("폰은 앞으로 1칸만 이동할 수 있습니다.");
        }
    }

    private void validateFirstTurnMove(final Position currentPosition, final Position destinationPosition) {
        final int count = currentPosition.countMoveForward(destinationPosition);
        if (count != PAWN_DEFAULT_MOVE_COUNT && count != PAWN_FIRST_MOVE_COUNT) {
            throw new IllegalArgumentException("폰은 첫번째 턴에는 1칸 또는 2칸만 이동할 수 있습니다.");
        }
    }

    public Position capture(final Position currentPosition, final Position destinationPosition) {
        final int count = currentPosition.countMoveDiagonal(destinationPosition);
        if (count != PAWN_DEFAULT_MOVE_COUNT) {
            throw new IllegalArgumentException("폰은 상대 말이 존재할 경우만 대각선으로 1칸만 이동할 수 있습니다.");
        }
        return position = destinationPosition;
    }

    public Position getPosition() {
        return position;
    }
}
