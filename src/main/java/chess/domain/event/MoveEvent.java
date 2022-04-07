package chess.domain.event;

import java.util.Objects;

public final class MoveEvent extends Event {

    private final MoveCommand moveCommand;

    public MoveEvent(MoveCommand moveCommand) {
        this.moveCommand = moveCommand;
    }

    public MoveEvent(String description) {
        this.moveCommand = MoveCommand.ofEventDescription(description);
    }

    public boolean isInit() {
        return false;
    }

    public boolean isMove() {
        return true;
    }

    public MoveCommand toMoveCommand() {
        return moveCommand;
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
        return Objects.equals(moveCommand, moveEvent.moveCommand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveCommand);
    }

    @Override
    public String toString() {
        return "MoveEvent{" + "description='" + moveCommand + '\'' + '}';
    }
}
