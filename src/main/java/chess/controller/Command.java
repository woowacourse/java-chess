package chess.controller;

import java.util.Collections;
import java.util.List;

public class Command {

    static final int COMMAND_INDEX = 0;
    static final Command EMPTY_COMMAND = new Command(CommandType.EMPTY, Collections.emptyList());

    private final CommandType commandType;

    private final List<String> options;

    public Command(final CommandType commandType, List<String> options) {
        this.commandType = commandType;
        this.options = options;
    }

    public static Command from(List<String> commandWithOptions) {
        validateLengthNotZero(commandWithOptions);

        final CommandType commandType = CommandType.from(commandWithOptions);

        return new Command(commandType, commandWithOptions.subList(1, commandWithOptions.size()));
    }

    private static void validateLengthNotZero(List<String> commandWithOptions) {
        if (commandWithOptions.size() == 0) {
            throw new IllegalArgumentException("커맨드가 존재하지 않습니다.");
        }
    }

    public boolean isStart() {
        return commandType == CommandType.START;
    }

    public boolean isMove() {
        return commandType == CommandType.MOVE;
    }

    public boolean isStatus() {
        return commandType == CommandType.STATUS;
    }

    public boolean isEnd() {
        return commandType == CommandType.END;
    }

    public List<String> getOptions() {
        return options;
    }
}
