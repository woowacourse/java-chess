package chess;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command renderToCommand(String input) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    public static boolean isStartCommand(String input) {
        return START.command.equals(input);
    }

    public static boolean isEndCommand(String input) {
        return END.command.equals(input);
    }

    public static boolean isStatusCommand(String input) {
        return STATUS.command.equals(input);
    }
}
