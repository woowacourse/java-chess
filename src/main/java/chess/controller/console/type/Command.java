package chess.controller.console.type;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String commandInput) {
        return Arrays.stream(Command.values())
            .filter(command -> command.value.equals(commandInput))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("명령어를 잘못 입력했습니다."));
    }
}
