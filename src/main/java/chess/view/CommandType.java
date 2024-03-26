package chess.view;

import java.util.Arrays;
import java.util.List;

public enum CommandType {

    START("start", 0),
    MOVE("move", 2),
    END("end", 0);

    private static final String INVALID_COMMAND = "존재하지 않는 명령어 입니다.";
    private final String type;
    private final int argumentCount;

    CommandType(String type, int argumentCount) {
        this.type = type;
        this.argumentCount = argumentCount;
    }

    public static CommandType findByCommand(List<String> input) {
        return Arrays.stream(values())
                .filter(command -> command.type.equals(input.get(0)))
                .filter(command -> command.argumentCount == input.size() - 1)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND));
    }

    public static boolean exists(String type) {
        return Arrays.stream(values())
                .anyMatch(commandType -> commandType.type.equals(type));
    }

    public static boolean isValidArguments(List<String> input) {
        return Arrays.stream(values())
                .anyMatch(commandType -> commandType.argumentCount == input.size() - 1);
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isEnd() {
        return this == END;
    }
}
