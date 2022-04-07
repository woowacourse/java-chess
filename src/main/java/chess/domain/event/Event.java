package chess.domain.event;

import java.util.Objects;

public class Event {

    private final EventType type;
    private final String description;

    public Event(EventType type, String description) {
        this.type = type;
        this.description = description;
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
