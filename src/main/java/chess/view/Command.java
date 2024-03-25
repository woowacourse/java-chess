package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"), END("end"), MOVE("move");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command inputToCommend(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("빈 값 입력을 허용하지 않습니다.");
        }
        return Arrays.stream(values())
                .filter(command -> command.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("옳바르지 않은 명령어 입력입니다."));
    }
}
