package chess.controller;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    END("end");

    private final String value;

    GameCommand(String value) {
        this.value = value;
    }

    public static GameCommand of(String command) {
        return Arrays.stream(values())
                .filter(gameStartCommand -> gameStartCommand.value.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 커멘드가 존재하지 않습니다."));
    }
}
