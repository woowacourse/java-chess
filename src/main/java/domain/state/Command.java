package domain.state;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
            .filter(it -> it.command.equals(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }
}
