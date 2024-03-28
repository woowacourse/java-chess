package chess.util;

import chess.exception.InvalidCommandException;
import java.util.Arrays;

public enum GameCommand {
    START("start"),
    LOAD("load"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    GameCommand(String value) {
        this.value = value;
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
