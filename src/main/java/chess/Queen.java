package chess;

public class Queen extends AbstractPiece {

    public Queen(final Position position) {
        super(position);
        validateInitPosition(position);
    }

    private void validateInitPosition(final Position position) {
        if (!position.isQueenPosition(position)) {
            throw new IllegalArgumentException(position.toString() + "는 퀸의 초기 위치가 아닙니다.");
        }
    }
}
