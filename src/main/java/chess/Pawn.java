package chess;

public class Pawn extends AbstractPiece {

    public Pawn(final Position position) {
        super(position);
        validateInitPosition(position);
    }

    private static void validateInitPosition(final Position position) {
        if (!position.isPawnPosition(position)) {
            throw new IllegalArgumentException(position.toString() + "는 폰의 초기 위치가 아닙니다.");
        }
    }
}
