package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final int ARGUMENT_START_INDEX = 1;

    private final String operation;

    Command(String operation) {
        this.operation = operation;
    }

    public static Command getByInput(String input) {

        return Arrays.stream(Command.values())
            .filter(command -> command.operation.equalsIgnoreCase(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 입력입니다."));
    }

    public static List<String> getArguments(List<String> input) {
        return input.subList(ARGUMENT_START_INDEX, input.size());
    }
}
