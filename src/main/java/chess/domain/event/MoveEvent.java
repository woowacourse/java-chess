package chess.domain.event;

import java.util.Objects;

public final class MoveEvent extends Event {

    private final String description;

    public MoveEvent(String description) {
        this.description = description;
    }

    public boolean isInit() {
        return false;
    }

    public boolean isMove() {
        return true;
    }

    public MoveCommand toMoveCommand() {
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
        MoveEvent moveEvent = (MoveEvent) o;
        return Objects.equals(description, moveEvent.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return "MoveEvent{" + "description='" + description + '\'' + '}';
    }
}
