package chess.domain.command;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final String INVALID_COMMAND_EXCEPTION_MESSAGE = "존재하지 않는 입력입니다.";

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
            .filter(command -> command.command.equals(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_EXCEPTION_MESSAGE));
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

}
