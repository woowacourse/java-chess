package chess.domain.command;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUE("status"),
    ;

    private static final String NO_SUCH_INPUT_ERROR_MESSAGE = "없는 명령어를 입력했습니다";

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command find(String input) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_INPUT_ERROR_MESSAGE));
    }

    public String get() {
        return command;
    }
}
