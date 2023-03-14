package chess.view;

import java.util.Arrays;
import java.util.Objects;

public enum Command {
    START("start"),
    END("end");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter(command -> Objects.equals(command.value, input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 명령어가 없습니다."));
    }
}
