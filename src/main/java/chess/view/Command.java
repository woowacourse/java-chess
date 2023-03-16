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

    static Command from(final String query) {
        return Arrays.stream(Command.values())
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
