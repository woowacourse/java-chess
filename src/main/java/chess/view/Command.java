package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static boolean isStart(String value) {
        return START == find(value);
    }

    public static boolean isEnd(String value) {
        return END == find(value);
    }

    public static boolean isMove(String value) {
        return MOVE == find(value);
    }

    private static Command find(String value) {
        return Arrays.stream(Command.values())
                .filter(command -> command.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 명령어는 존재하지 않습니다."));
    }

}
