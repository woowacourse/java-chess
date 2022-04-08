package chess.domain.event;

import chess.domain.command.MoveRoute;

public abstract class Event {

    public static Event of(String type, String description) {
        EventType eventType = EventType.valueOf(type);
        if (eventType == EventType.MOVE) {
            return new MoveEvent(description);
        }
        return new InitEvent();
    }

    public abstract boolean isInit();

    public abstract boolean isMove();

    public abstract MoveRoute toMoveRoute();

    public abstract EventType getType();

    public abstract String getDescription();
}
