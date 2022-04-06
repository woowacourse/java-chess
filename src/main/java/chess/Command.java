package chess;

import java.util.Arrays;

public enum Command {
    START, END, MOVE, STATUS;

    public static Command of(String command) {
        String capitalizedCommand = command.toUpperCase();
        if (Arrays.asList(values()).contains(capitalizedCommand)) {
            return valueOf(capitalizedCommand);
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}
