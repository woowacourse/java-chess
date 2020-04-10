package chess.domain.move;

public class KnightMove implements Move {
    private static final String ERROR_MESSAGE_UNSUPPORTED_METHOD = "지원하지 않는 메소드 입니다";

    @Override
    public Direction getDirection() {
        throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORTED_METHOD);
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORTED_METHOD);
    }
}
