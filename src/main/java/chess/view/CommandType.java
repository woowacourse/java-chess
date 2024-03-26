package chess.view;

import java.util.Arrays;

public enum CommandType {

    START("start", 0),
    MOVE("move", 2),
    END("end", 0);

    private static final String ERROR_INVALID_COMMAND = " 은(는) 존재하지 않는 명령어 입니다.";
    private final String value;
    private final int argumentCount;

    CommandType(final String value, final int argumentCount) {
        this.value = value;
        this.argumentCount = argumentCount;
    }

    public static CommandType from(final String input) {
        return Arrays.stream(values())
                .filter(commandType -> commandType.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(input + ERROR_INVALID_COMMAND));
    }

    public int getArgumentCount() {
        return argumentCount;
    }
}
