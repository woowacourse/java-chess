package chess.controller;

import java.util.List;

public class OptionLessCommandDto implements CommandDto {
    private static final int COMMAND_INDEX = 0;
    private final String command;

    public OptionLessCommandDto(final String command) {
        this.command = command;
    }

    public OptionLessCommandDto(final List<String> commandWithOptions) {
        this(commandWithOptions.get(COMMAND_INDEX));
    }

    @Override
    public String getCommand() {
        return command;
    }

}
