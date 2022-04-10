package chess.domain.event;

import chess.domain.command.MoveRoute;

public final class InitEvent extends Event {

    private static final String INIT_DESCRIPTION = "";

    public boolean isInit() {
        return true;
    }

    public boolean isMove() {
        return false;
    }

    public MoveRoute toMoveRoute() {
        throw new UnsupportedOperationException("이동 이벤트가 아닙니다.");
    }

    public EventType getType() {
        return EventType.INIT;
    }

    public String getDescription() {
        return INIT_DESCRIPTION;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
