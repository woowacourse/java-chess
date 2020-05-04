package chess.ui;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    static Command of(String value) {
        return Arrays.stream(values())
                .filter(command -> command.matchValue(value, command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s에 해당하는 명령어를 찾을 수 없습니다.", value)));
    }

    public boolean isNotMove() {
        return this != MOVE;
    }

    public boolean isNotStart() {
        return this != START;
    }

    public boolean isNotEnd() {
        return this != END;
    }

    public boolean isNotStatus() {
        return this != STATUS;
    }

    private boolean matchValue(String value, Command command) {
        return command.value.equals(value);
    }
}
