package chess.view;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move");

    private final String code;

    GameCommand(String code) {
        this.code = code;
    }

    public static GameCommand from(String code) {
        return Arrays.stream(values())
                .filter(code::equals)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("해당 커맨드를 찾을 수 없습니다"));
    }
}
