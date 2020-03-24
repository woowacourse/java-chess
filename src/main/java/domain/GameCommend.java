package domain;

import java.util.Arrays;

public enum GameCommend {
    START("start"),
    END("end");

    private String commend;

    GameCommend(String commend) {
        this.commend = commend;
    }

    public static GameCommend answer(String input) {
        return Arrays.stream(GameCommend.values())
            .filter(gameCommend -> gameCommend.commend.equals(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("start 또는 end를 입력하세요."));
    }
}
