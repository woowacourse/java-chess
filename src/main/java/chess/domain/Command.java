package chess.domain;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    ;

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command from(final String command) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다."));
    }
}
