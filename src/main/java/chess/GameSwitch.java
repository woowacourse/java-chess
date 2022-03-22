package chess;

import java.util.Arrays;

public enum GameSwitch {

    START("start"),
    END("end");

    private final String value;

    GameSwitch(final String value) {
        this.value = value;
    }

    public static GameSwitch of(final String value) {
        return Arrays.stream(values())
                .filter(gameSwitch -> gameSwitch.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다."));
    }
}
