package chess.domain;

import java.util.Arrays;
import java.util.List;

public enum CommandType {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;
    private static final String COMMAND_ERROR = "[ERROR] 올바른 명령이 아닙니다.";
    private static final List<CommandType> FIRST_COMMAND = Arrays.asList(START, END);
    private static final List<CommandType> RUNNING_COMMAND = Arrays.asList(END, MOVE, STATUS);

    CommandType(String value) {
        this.value = value;
    }

    public static CommandType findFirstCommand(String input) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.value.equals(input))
                .filter(commandType -> FIRST_COMMAND.contains(commandType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_ERROR));
    }

    public static CommandType findRunningCommand(String input) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.value.equals(input))
                .filter(commandType -> RUNNING_COMMAND.contains(commandType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_ERROR));
    }
}
