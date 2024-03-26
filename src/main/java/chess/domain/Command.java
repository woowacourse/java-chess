package chess.domain;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move");

    private final String message;

    Command(final String message) {
        this.message = message;
    }

    public static Command from(final String message) {
        return Arrays.stream(values())
                .filter(command -> command.message.equals(message))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("[ERROR] 입력은 %s, %s로 해야 합니다.", START.message, END.message)));
    }

    public boolean isStart() {
        return this.equals(Command.START);
    }

    public String getMessage() {
        return message;
    }
}
