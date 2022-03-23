package chess.piece;

public class Bishop extends AbstractPiece {

    public Bishop(final Position position) {
        super(position);
        validateInitPosition(position);
    }

    private static void validateInitPosition(final Position position) {
        if (!position.isBishopPosition(position)) {
            throw new IllegalArgumentException(position.toString() + "는 비숍의 초기 위치가 아닙니다.");
        }
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.BISHOP);
    }
}
