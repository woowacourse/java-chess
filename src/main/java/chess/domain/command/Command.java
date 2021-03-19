package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public enum Command {
    START("start", 0),
    END("end", 0),
    MOVE("move", 2),
    STATUS("status", 0);

    private static final String DELIMITER = " ";
    private static final int OPERATION_INDEX = 0;
    public static final int ARGUMENT_START_INDEX = 1;

    private final String operation;
    private final int argumentCount;

    Command(String operation, int argumentCount) {
        this.operation = operation;
        this.argumentCount = argumentCount;
    }

    public static Command getByInput(String input) {
        List<String> splitInputs = Arrays.asList(input.split(DELIMITER));
        String firstInput = splitInputs.get(OPERATION_INDEX);
        int inputArgumentsCount = splitInputs.size() - 1;

        return Arrays.stream(Command.values())
            .filter(command -> command.operation.equalsIgnoreCase(firstInput)
                && command.argumentCount == inputArgumentsCount)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 입력입니다."));
    }

    public static List<String> getArguments(String input) {
        List<String> splitInputs = Arrays.asList(input.split(DELIMITER));
        return splitInputs.subList(ARGUMENT_START_INDEX, splitInputs.size());
    }
}
