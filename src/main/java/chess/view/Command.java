package chess.view;

import java.util.Arrays;

public enum Command {

    LOGIN("login"),
    REGISTER("register"),
    START("start"),
    NEW("new"),
    EXIST("exist"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command from(final String command) {
        return Arrays.stream(values())
                .filter(it -> it.value.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 커멘드입니다."));
    }
}
