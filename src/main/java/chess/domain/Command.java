package chess.domain;

import java.util.Arrays;

public enum Command {

    START("start"), END("end");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command from(final String input) {
        validateBlank(input);

        return Arrays.stream(values())
                .filter(command -> command.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("[ERROR] 입력은 %s, %s로 해야 합니다.", START.value, END.value)));
    }

    private static void validateBlank(final String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 명령어입니다.");
        }
    }
}
