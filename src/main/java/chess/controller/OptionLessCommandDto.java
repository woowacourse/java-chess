package chess.controller;

import java.util.List;
import java.util.Objects;

public class OptionLessCommandDto implements CommandDto {
    private static final int COMMAND_INDEX = 0;
    private final String command;

    public OptionLessCommandDto(final String command) {
        validateNotOptionCommand(command);
        this.command = command;
    }

    private void validateNotOptionCommand(final String command) {
        if (Objects.equals(command, "move")) {
            throw new IllegalArgumentException("잘못된 명령어입니다");
        }
    }

    public OptionLessCommandDto(final List<String> commandWithOptions) {
        this(commandWithOptions.get(COMMAND_INDEX));
    }

    @Override
    public String getCommand() {
        return command;
    }
}
