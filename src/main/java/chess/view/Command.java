package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command find(String value) {
        return Arrays.stream(Command.values())
                .filter(command -> command.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 명령어는 존재하지 않습니다."));
    }

    public String getValue() {
        return value;
    }
}
