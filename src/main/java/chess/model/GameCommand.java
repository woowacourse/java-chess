package chess.model;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    MOVE("move"),
    END("end");

    private final String text;

    GameCommand(String text) {
        this.text = text;
    }

    public static GameCommand createFirstGameCommand(String input) {
        return Arrays.stream(values())
                .filter(value -> value.text.equals(input))
                .filter(value -> !MOVE.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start 혹은 end를 입력해주세요."));
    }

    public static GameCommand createMoveCommand(String input) {
        return Arrays.stream(values())
                .filter(value -> value.text.equals(input))
                .filter(value -> !START.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("move 혹은 end를 입력해주세요."));
    }

    public boolean isEnd() {
        return this.equals(END);
    }
}
