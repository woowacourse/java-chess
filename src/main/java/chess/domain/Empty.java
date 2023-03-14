package chess.domain;

public class Empty extends Piece {

    public Empty(final Type type, final Side side) {
        super(type, side);
    }

    @Override
    protected void validate(final Type type, final Side side) {
        validateType(type);
        validateSide(side);
    }

    private void validateType(final Type type) {
        if (type != Type.EMPTY) {
            throw new IllegalArgumentException("Empty의 타입이 잘못되었습니다.");
        }
    }

    private void validateSide(final Side side) {
        if (side != Side.NEUTRALITY) {
            throw new IllegalArgumentException("Empty는 진영을 가질 수 없습니다.");
        }
    }
}
