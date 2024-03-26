package chess.view;

import chess.exception.InvalidCommandException;
import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    GameCommand(String value) {
        this.value = value;
    }

    public static boolean isStart(String value) {
        return START.value.equals(value);
    }

    public static GameCommand find(String value) {
        return Arrays.stream(GameCommand.values())
                .filter(gameCommand -> gameCommand.value.equals(value))
                .findAny()
                .orElseThrow(() -> new InvalidCommandException("해당 명령어는 존재하지 않습니다: %s".formatted(value)));
    }

    public String getValue() {
        return value;
    }
}
