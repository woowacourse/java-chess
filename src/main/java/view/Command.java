package view;

import domain.Board;
import exception.GameFinishedException;

public class Command {
    private final CommandType commandType;
    private final String value;

    public Command(String value) {
        this.commandType = CommandType.findByCommand(value);
        this.value = value;
    }

    public void execute(Board board) {
        if (commandType.isStart()) {
            board.initialize();
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
}
