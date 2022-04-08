package chess.domain.event;

import chess.domain.command.MoveRoute;
import java.util.Objects;

public final class MoveEvent extends Event {

    private final MoveRoute moveRoute;

    public MoveEvent(MoveRoute moveRoute) {
        this.moveRoute = moveRoute;
    }

    public MoveEvent(String description) {
        this(MoveRoute.ofEventDescription(description));
    }

    public boolean isInit() {
        return false;
    }

    public boolean isMove() {
        return true;
    }

    public MoveRoute toMoveRoute() {
        return moveRoute;
    }

    public EventType getType() {
        return EventType.MOVE;
    }

    public String getDescription() {
        return moveRoute.toDescription();
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
        return Objects.equals(moveRoute, moveEvent.moveRoute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveRoute);
    }

    @Override
    public String toString() {
        return "MoveEvent{" + "description='" + moveRoute + '\'' + '}';
    }
}
