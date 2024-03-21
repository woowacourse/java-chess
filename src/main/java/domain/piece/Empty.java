package domain.piece;

import domain.position.Position;

public class Empty extends AbstractPiece {
    private static final Empty instance = new Empty();

    private Empty() {
        super(Color.NEUTRALITY);
    }

    public static Empty getInstance() {
        return instance;
    }

    @Override
    public void validateMovement(Position resource, Position target, Piece other) {
        throw new UnsupportedOperationException("Empty는 움직일 수 없습니다."); // todo 메시지 수정
    }

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Empty는 타입이 없습니다.");
    }
}
