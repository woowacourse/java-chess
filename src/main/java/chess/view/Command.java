package chess.view;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    ;

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command from(String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 커맨드입니다."));
    }
}
