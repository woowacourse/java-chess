package chess.domain.command;

import java.util.Arrays;

public enum CommandCase {

    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    CommandCase(String value) {
        this.value = value;
    }

    public static CommandCase from(final String input) {
        return Arrays.stream(CommandCase.values())
                .filter(commandCase -> commandCase.getValue().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력값은 start, end, move만 가능합니다."));
    }

    public String getValue() {
        return value;
    }
}
