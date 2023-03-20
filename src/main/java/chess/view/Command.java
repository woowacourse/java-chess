package chess.view;

import java.util.Arrays;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static boolean isEnd(String command) {
        return Arrays.stream(values())
                     .anyMatch(it -> it.value.equals(command) && command.equals("end"));
    }

    public static boolean isMove(String command) {
        return Arrays.stream(values())
                     .anyMatch(it -> it.value.equals(command) && command.equals("move"));
    }

    public static boolean isNotStart(String command) {
        return Arrays.stream(values())
                     .anyMatch(it -> it.value.equals(command) && !command.equals("start"));
    }

    public static boolean isNotAppropriate(String command) {
        return Arrays.stream(values())
                     .filter(it -> it != MOVE)
                     .noneMatch(it -> it.value.equals(command));
    }
}
