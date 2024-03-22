package domain.piece;

import domain.position.Position;

public class Empty implements Piece {
    private static final Empty INSTANCE = new Empty();

    private Empty() {
    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public void validate(Position resource, Position target, Piece other) {
        throw new UnsupportedOperationException("Empty는 움직일 수 없습니다."); // todo 메시지 수정
    }

    @Override
    public Color color() {
        return Color.NEUTRALITY;
    }

    @Override
    public Type type() {
        throw new UnsupportedOperationException("Empty는 타입이 없습니다.");
    }
}
