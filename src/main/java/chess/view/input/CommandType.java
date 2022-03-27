package chess.view.input;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    CommandType(final String value) {
        this.value = value;
    }

    public static CommandType from(final String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
