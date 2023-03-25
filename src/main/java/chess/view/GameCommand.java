package chess.view;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status");

    private final String text;

    GameCommand(final String text) {
        this.text = text;
    }

    public static GameCommand of(String text) {
        return Arrays.stream(values())
                .filter(value -> value.text.equals(text))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 입력값에 해당하는 게임 명령어가 존재하지 않습니다."));
    }

    public String getText() {
        return text;
    }
}
