package chess.domain.event;

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

    public abstract MoveCommand toMoveCommand();

    public abstract EventType getType();

    public abstract String getDescription();
}
