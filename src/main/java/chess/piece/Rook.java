package chess.piece;

public class Rook extends AbstractPiece {

    public Rook(final Position position) {
        super(position);
        validateInitPosition(position);
    }

    private static void validateInitPosition(final Position position) {
        if (!position.isRookPosition(position)) {
            throw new IllegalArgumentException(position.toString() + "는 룩의 초기 위치가 아닙니다.");
        }
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.ROOK);
    }
}
