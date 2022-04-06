package application.console;

import java.util.Arrays;

public enum Command {

    START("START", 0),
    MOVE("MOVE", 2),
    STATUS("STATUS", 0),
    END("END", 0),
    ;

    private final String command;
    private final int optionCount;

    Command(final String command, final int optionCount) {
        this.command = command;
        this.optionCount = optionCount;
    }

    public static Command from(final String command) {
        return Arrays.stream(values())
                .filter(it -> command.equalsIgnoreCase(it.command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("명령어를 잘못 입력하셨습니다."));
    }

    public boolean isStartCommand() {
        return this == START;
    }

    public boolean isMoveCommand() {
        return this == MOVE;
    }

    public boolean isStatusCommand() {
        return this == STATUS;
    }

    public boolean isEndCommand() {
        return this == END;
    }

    public boolean equalsOptionCount(final int optionCount) {
        return this.optionCount == optionCount;
    }
}
