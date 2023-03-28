package chess.controller.command;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String command;

    CommandType(final String command) {
        this.command = command;
    }

    public static CommandType from(final String query) {
        return Arrays.stream(CommandType.values())
                .filter(a -> a.equalsByQuery(query))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start 혹은 end 혹은 move 를 입력해야합니다."));
    }

    private boolean equalsByQuery(final String query) {
        return command.equalsIgnoreCase(query);
    }

    public String value() {
        return this.command;
    }
}
