package chess.domain;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    GameCommand(String value) {
        this.value = value;
    }

    public static GameCommand from(String inputCommand) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(inputCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 커맨드입니다."));
    }

    public boolean isStart() {
        return this == START;
    }
}
