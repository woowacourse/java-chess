package chess.view;

import java.util.Arrays;

public enum Commands {

    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    public static final String EXCEPTION_INPUT = "입력은 start, end, status, move 중 하나로 입력하여 주세요";

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public static Commands getCommand(String value) {
        return Arrays.stream(Commands.values())
            .filter(command -> command.command.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(EXCEPTION_INPUT));
    }
}
