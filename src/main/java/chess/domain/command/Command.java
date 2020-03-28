package chess.domain.command;

import chess.domain.position.Position;
import utils.CommandParser;

import java.util.Objects;

public class Command {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private String[] command;

    private Command(String command) {
        Objects.requireNonNull(command, "null이 입력되었습니다.");
        this.command = CommandParser.parseCommand(command);

        validateCommand(this.command[COMMAND_INDEX]);
    }

    public static Command of(String command) {
        return new Command(command);
    }

    private void validateCommand(String command) {
        if (command.equals("start") || command.equals("end") || command.equals("status") || command.equals("move")) {
            return;
        }
        throw new IllegalArgumentException("잘못된 명령입니다.");
    }

    public Position sourcePosition() {
        return Position.of(this.command[SOURCE_POSITION_INDEX]);
    }

    public Position targetPosition() {
        return Position.of(this.command[TARGET_POSITION_INDEX]);
    }

    public boolean isStart() {
        return this.command[COMMAND_INDEX].equals("start");
    }

    public boolean isEnd() {
        return this.command[COMMAND_INDEX].equals("end");
    }

    public boolean isMove() {
        return this.command[COMMAND_INDEX].equals("move");
    }

    public boolean isStatus() {
        return this.command[COMMAND_INDEX].equals("status");
    }

}
