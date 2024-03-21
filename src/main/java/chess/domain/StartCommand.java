package chess.domain;

import java.util.Arrays;

public enum StartCommand {

    START("start"),
    END("end");

    private final String message;

    StartCommand(final String message) {
        this.message = message;
    }

    public static StartCommand from(final String input) {
        validateBlank(input);

        return Arrays.stream(values())
                .filter(command -> command.message.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("[ERROR] 입력은 %s, %s로 해야 합니다.", START.message, END.message)));
    }

    private static void validateBlank(final String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 명령어입니다.");
        }
    }

    public String getMessage() {
        return message;
    }
}
