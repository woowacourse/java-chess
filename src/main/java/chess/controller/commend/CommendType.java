package chess.controller.commend;

import java.util.Arrays;

public enum CommendType {
    START("start"),
    END("end"),
    MOVE("move");
    private final String value;

    CommendType(String value) {
        this.value = value;
    }

    public static CommendType match(String input) {
        return Arrays.stream(values())
                .filter(commendType -> commendType.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
