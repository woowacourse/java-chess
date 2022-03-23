package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public static void validate(String input) {
         Arrays.stream(values())
                .filter(it -> it.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }

    public String getValue() {
        return value;
    }
}
