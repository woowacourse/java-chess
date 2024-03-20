package chess.model;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end");

    private final String displayName;

    Command(String displayName) {
        this.displayName = displayName;
    }

    public static Command findCommand(String displayName) {
        return Arrays.stream(values())
                .filter(command -> command.displayName.equals(displayName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 게임 명령어입니다."));
    }

    public boolean isRunning() {
        return this != END;
    }
}
