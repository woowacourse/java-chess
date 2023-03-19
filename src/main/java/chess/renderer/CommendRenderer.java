package chess.renderer;

import chess.controller.commend.CommendType;

import java.util.Arrays;

public enum CommendRenderer {
    START("start", CommendType.START),
    END("end", CommendType.END),
    MOVE("move", CommendType.MOVE);
    private final String value;
    private final CommendType commendType;

    CommendRenderer(String value, CommendType commendType) {
        this.value = value;
        this.commendType = commendType;
    }

    public static CommendType render(String input) {
        return Arrays.stream(values())
                .filter(commendType -> commendType.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException())
                .commendType;
    }
}
