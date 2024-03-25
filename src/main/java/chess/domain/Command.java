package chess.domain;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end");

    private final String message;

    Command(final String message) {
        this.message = message;
    }

    public static Command from(final String input) {
        return Arrays.stream(values())
                .filter(command -> command.message.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("[ERROR] 입력은 %s, %s로 해야 합니다.", START.message, END.message)));
    }

    public boolean isStartCommand() {
        return this.equals(Command.START);
    }

    public String getMessage() {
        return message;
    }
}
