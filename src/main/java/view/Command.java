package view;

import domain.Board;
import exception.GameFinishedException;

import java.util.Objects;

public class Command {
    private final CommandType commandType;
    private final String value;

    public Command(String value) {
        this.commandType = CommandType.findByCommand(value);
        this.value = value;
    }

    public void execute(Board board) {
        if (commandType.isStart()) {
            board.reset();
        }

        if (commandType.isMoving()) {
            String[] split = value.split(" ");
            String fromPoint = split[1];
            String toPoint = split[2];
            board.move(fromPoint, toPoint);
        }

        if (commandType.isEnd()) {
            throw new GameFinishedException();
        }
    }

    public boolean isStarting() {
        return this.commandType == CommandType.START;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return commandType == command.commandType && value.equals(command.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType, value);
    }
}
