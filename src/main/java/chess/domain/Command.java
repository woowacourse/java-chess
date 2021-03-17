package chess.domain;

import java.util.stream.Stream;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String message;

    Command (String message) {
        this.message = message;
    }

    public static Command of(String value) {
        return Stream.of(values())
                .filter(command -> command.message.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 명령어입니다."));
    }

    public static boolean isStart(String input) {
        return of(input).equals(START);
    }

    public static boolean isEnd(String input) {
        return of(input).equals(END);
    }
}
