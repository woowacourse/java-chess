package chess.controller;

import java.util.List;

public interface Command {

    final int COMMAND_INDEX = 0;
    Command EMPTY_COMMAND = () -> CommandType.EMPTY;

    static Command from(List<String> commandWithOptions) {
        validateLengthNotZero(commandWithOptions);

        if (commandWithOptions.size() == 1) {
            return new OptionLessCommand(commandWithOptions);
        }

        return MoveCommand.from(commandWithOptions);
    }

    private static void validateLengthNotZero(List<String> commandWithOptions) {
        if (commandWithOptions.size() == 0) {
            throw new IllegalArgumentException("커맨드가 존재하지 않습니다.");
        }
    }

    CommandType getCommandType();
}
