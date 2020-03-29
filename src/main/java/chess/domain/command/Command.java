package chess.domain.command;

import java.util.Arrays;

import static chess.util.NullValidator.validateNull;

public enum Command {
    START("start"),
    STATUS("status"),
    END("end");

    private final String commandString;

    Command(String commandString) {
        this.commandString = commandString;
    }

    public static Command of(String input) {
        validateNull(input);

        return Arrays.stream(values())
                .filter(v -> v.isEqualTo(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }

    private boolean isEqualTo(String input) {
        return this.commandString.equals(input);
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }
}
