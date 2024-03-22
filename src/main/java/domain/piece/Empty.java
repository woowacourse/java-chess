package domain.piece;

import domain.position.Position;

public class Empty implements Piece {
    private static final Empty INSTANCE = new Empty();

    private Empty() {
    }

    public static Empty create() {
        return INSTANCE;
    }

    @Override
    public void validateMovement(Position source, Position target, Piece other) {
        throw new UnsupportedOperationException("Empty Piece는 움직일 수 없습니다.");
    }

    @Override
    public Color color() {
        return Color.NEUTRALITY;
    }

    @Override
    public Type type() {
        throw new UnsupportedOperationException("Empty Piece는 타입이 존재하지 않습니다.");
    }
}
