package chess.view.command;

import java.util.Arrays;

public enum StartCommand {

    START("start"),
    END("end");

    private final String message;

    StartCommand(final String value) {
        this.message = value;
    }

    public static StartCommand of(final String value) {
        return Arrays.stream(values())
                .filter(command -> value.equals(command.message))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "%s 또는 %s로 입력해주세요.".formatted(START.message, END.message)));
    }

    public boolean isStart() {
        return this == START;
    }

    public String getMessage() {
        return message;
    }
}
