package chess.domain;

public class Knight extends Piece {

    public Knight(final Type type, final Side side) {
        super(type, side);
    }

    @Override
    protected void validate(final Type type, final Side side) {
        validateType(type);
        validateSide(side);
    }

    private void validateType(final Type type) {
        if (type != Type.KNIGHT) {
            throw new IllegalArgumentException("나이트의 타입이 잘못되었습니다.");
        }
    }

    private void validateSide(final Side side) {
        if (side == Side.NEUTRALITY) {
            throw new IllegalArgumentException("나이트는 중립적인 기물이 아닙니다.");
        }
    }
}
