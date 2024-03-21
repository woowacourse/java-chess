package chess.view;

import java.util.Arrays;

public enum Commend {
    START("start"), END("end"), MOVE("move");

    private final String value;

    Commend(String value) {
        this.value = value;
    }

    public static Commend inputToCommend(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("빈 값 입력을 허용하지 않습니다.");
        }
        return Arrays.stream(values())
                .filter(commend -> commend.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("옳바르지 않은 명령어 입력입니다."));
    }
}
