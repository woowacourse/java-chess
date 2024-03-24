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

    public static Command fromStartCommand(final String input) {
        validateBlank(input);

        return Arrays.stream(values())
                .filter(command -> command.message.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("[ERROR] 게임 시작, 종료 명령은 %s, %s로 해야 합니다.", START.message, END.message)));
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
