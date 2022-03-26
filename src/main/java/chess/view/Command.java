package chess.view;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status")
    ;

    private final String type;

    Command(String type) {
        this.type = type;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter(command -> input.equals(command.type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isStatus() {
        return this == STATUS;
    }
}
