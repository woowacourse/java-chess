package chess.controller.state;

import java.util.Arrays;

public enum Turn {
    START("start"),
    WHITE_TURN("white_turn"),
    BLACK_TURN("black_turn"),
    END("end"),
    ;

    private final String value;

    Turn(String value) {
        this.value = value;
    }

    public static Turn from(String input) {
        return Arrays.stream(values())
                .filter(turn -> turn.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 기물입니다."));
    }
}
