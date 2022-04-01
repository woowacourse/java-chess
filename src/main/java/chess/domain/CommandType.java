package chess.domain;

import java.util.Arrays;

public enum CommandType {
    START(true),
    END(true),
    STATUS(true),
    MOVE(false);

    private final boolean isSingleCommand;

    CommandType(boolean isSingleCommand) {
        this.isSingleCommand = isSingleCommand;
    }

    public static CommandType of(String commandName) {
        return Arrays.stream(values()).filter(commandType -> commandName.equalsIgnoreCase(commandType.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효한 커맨드가 아닙니다."));
    }

    public static boolean isSingleCommand(CommandType command) {
        return command.isSingleCommand;
    }
}
