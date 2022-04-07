package chess.domain.event;

import java.util.Objects;

public class Event {

    private final EventType type;
    private final String description;

    private Event(EventType type, String description) {
        this.type = type;
        this.description = description;
    }

    public static Event of(String type, String description) {
        return new Event(EventType.valueOf(type), description);
    }

    public static Event ofMove(String description) {
        return new Event(EventType.MOVE, description);
    }

    public boolean isInit() {
        return type == EventType.INIT;
    }

    public boolean isMove() {
        return type == EventType.MOVE;
    }

    public MoveCommand toMoveCommand() {
        if (type != EventType.MOVE) {
            throw new UnsupportedOperationException("이동 이벤트가 아닙니다.");
        }
        return MoveCommand.ofEventDescription(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return type == event.type
                && Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, description);
    }

    @Override
    public String toString() {
        return "Event{" + "type=" + type + ", description='" + description + '\'' + '}';
    }
}
