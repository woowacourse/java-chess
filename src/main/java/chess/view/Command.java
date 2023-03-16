package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command from(final String input) {
        return Arrays.stream(Command.values())
                .filter(it -> it.command.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 명령어입니다."));
    }
}
