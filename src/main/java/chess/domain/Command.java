package chess.domain;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command from(final String input) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start 또는 end를 입력해주세요."));
    }
    public static boolean isEnd(final String input) {
        return from(input) == END;
    }
}
