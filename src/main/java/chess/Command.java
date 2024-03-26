package chess;

import java.util.Arrays;

public enum Command {
    END("end"), START("start"), MOVE("move");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter((command) -> command.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }
}
