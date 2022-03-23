package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    ;

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command from(final String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
