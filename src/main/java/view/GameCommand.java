package view;

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

    public static boolean notExist(final String commandInput) {
        return Arrays.stream(values())
            .noneMatch(command -> command.value.equals(commandInput));
    }

    public static GameCommand find(final String commandInput) {
        return Arrays.stream(values())
            .filter(command -> command.value.equals(commandInput))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드입니다."));
    }
}
