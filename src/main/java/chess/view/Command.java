package chess.view;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command of(final String value) {
        return Arrays.stream(values())
                .filter(gameSwitch -> gameSwitch.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다."));
    }

    public String getValue() {
        return value;
    }
}
