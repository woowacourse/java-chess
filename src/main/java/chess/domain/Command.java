package chess.domain;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 run을 찾을 수 없습니다."));
    }

    public boolean isNotStart() {
        return  this != START;
    }
}
