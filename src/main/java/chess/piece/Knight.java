package chess.piece;

public class Knight extends AbstractPiece {

    public Knight(final Position position) {
        super(position);
        validateInitPosition(position);
    }

    private static void validateInitPosition(final Position position) {
        if (!position.isKnightPosition(position)) {
            throw new IllegalArgumentException(position.toString() + "는 나이트의 초기 위치가 아닙니다.");
        }
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.KNIGHT);
    }
}
