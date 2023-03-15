package chess.view;

import java.util.Arrays;

public enum Command {
    START("start", true),
    END("end", false);

    private final String command;
    private final boolean condition;

    Command(final String command, final boolean condition) {
        this.command = command;
        this.condition = condition;
    }

    static Command from(final String query) {
        return Arrays.stream(Command.values())
                .filter(a -> a.equalsByQuery(query))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start 혹은 end를 입력해야합니다."));
    }

    private boolean equalsByQuery(final String query) {
        return command.equalsIgnoreCase(query);
    }

    public boolean getCondition() {
        return condition;
    }

    public String value() {
        return this.command;
    }
}
