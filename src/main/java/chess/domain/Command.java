package chess.domain;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    ;

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command of(final String command) {
        return Arrays.stream(Command.values())
            .filter(value -> value.getCommand().equals(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다."));
    }

    public String getCommand() {
        return command;
    }
}
