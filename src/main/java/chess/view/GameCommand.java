package chess.view;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end");

    private final String text;

    GameCommand(String text) {
        this.text = text;
    }

    public static GameCommand from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.text.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start 혹은 end를 입력해주세요."));
    }

    public boolean isEnd() {
        return this.equals(END);
    }
}
