package chess.domain.event;

public final class InitEvent extends Event {

    public boolean isInit() {
        return true;
    }

    public boolean isMove() {
        return false;
    }

    public MoveCommand toMoveCommand() {
        throw new UnsupportedOperationException("이동 이벤트가 아닙니다.");
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
