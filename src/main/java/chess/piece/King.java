package chess.piece;

public class King extends AbstractPiece {

    public King(final Position position) {
        super(position);
        validateInitPosition(position);
    }

    private static void validateInitPosition(final Position position) {
        if (!position.isKingPosition(position)) {
            throw new IllegalArgumentException(position.toString() + "는 킹의 초기 위치가 아닙니다.");
        }
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.KING);
    }
}
