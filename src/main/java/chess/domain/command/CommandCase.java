package chess.domain.command;

import java.util.Arrays;

public enum CommandCase {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    CommandCase(String value) {
        this.value = value;
    }

    public static CommandCase from(final String input) {
        return Arrays.stream(CommandCase.values())
                .filter(commandCase -> commandCase.value().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력값은 start, end, move, status만 가능합니다."));
    }

    public String value() {
        return value;
    }
}
