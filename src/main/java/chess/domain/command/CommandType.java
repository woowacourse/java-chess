package chess.domain.command;

import java.util.Arrays;

public enum CommandType {

    START(true),
    END(true),
    STATUS(true),
    MOVE(false);

    private final boolean isSingleCommand;

    CommandType(final boolean isSingleCommand) {
        this.isSingleCommand = isSingleCommand;
    }

    public static CommandType of(final String commandName) {
        return Arrays.stream(values())
                .filter(commandType -> commandName.equalsIgnoreCase(commandType.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효한 커맨드가 아닙니다."));
    }

    public boolean isSingleCommand() {
        return isSingleCommand;
    }
}

