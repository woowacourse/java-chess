package chess.controller;

import java.util.List;
import java.util.Objects;

public class OptionLessCommand implements Command {

    private final CommandType commandType;

    public OptionLessCommand(final String command) {
        validateNotOptionCommand(command);
        this.commandType = CommandType.from(command);
    }

    private void validateNotOptionCommand(final String command) {
        if (Objects.equals(command, "move")) {
            throw new IllegalArgumentException("잘못된 명령어입니다");
        }
    }

    public OptionLessCommand(final List<String> commandWithOptions) {
        this(commandWithOptions.get(COMMAND_INDEX));
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}
