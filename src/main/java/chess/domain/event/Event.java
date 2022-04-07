package chess.domain.event;

public abstract class Event {

    public static Event of(String type, String description) {
        EventType eventType = EventType.valueOf(type);
        if (eventType == EventType.MOVE) {
            return ofMove(description);
        }
        return new InitEvent();
    }

    public static Event ofMove(String description) {
        return new MoveEvent(description);
    }

    public abstract boolean isInit();

    public abstract boolean isMove();

    public abstract MoveCommand toMoveCommand();
}
