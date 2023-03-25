package chess.domain.command;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status"),
    FIND_PREVIOUS_GAME("restart");

    private final String command;

    GameCommand(final String command) {
        this.command = command;
    }
    public static GameCommand from(String statusCommand) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(statusCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 커맨드를 입력해주세요."));
    }
}
