package chess.view;

import java.util.Arrays;

public enum Commands {

    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public static Commands getInstance(String value) {
        return Arrays.stream(Commands.values())
            .filter(command -> command.command.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }
}
