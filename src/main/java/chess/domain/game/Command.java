package chess.domain.game;

import java.util.Arrays;
import java.util.Objects;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String commandValue;

    Command(String command) {
        this.commandValue = command;
    }

    public static Command of(String commandInput) {
        Objects.requireNonNull(commandInput);
        return Arrays.stream(values())
                .filter(command -> command.commandValue.equals(commandInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입니다."));
    }
}
