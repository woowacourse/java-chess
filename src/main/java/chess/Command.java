package chess;

import java.util.Arrays;

public enum Command {

    START("start"),
    STATUS("status"),
    MOVE("move"),
    END("end");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String commandValue) {
        if (commandValue.startsWith(MOVE.value)) {
            return getMoveCommandOf(commandValue);
        }
        return Arrays.stream(values())
            .filter(command -> commandValue.equals(command.value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 명령이 존재하지 않습니다."));
    }

    private static Command getMoveCommandOf(String command) {
        String[] commands = command.split(" ");
        validateMoveCommandSize(commands);
        validateMoveCommandFormat(commands);
        return MOVE;
    }

    private static void validateMoveCommandSize(String[] commands) {
        if (commands.length != 3) {
            throw new IllegalArgumentException("해당 명령이 존재하지 않습니다.");
        }
    }

    private static void validateMoveCommandFormat(String[] commands) {
        if (!commands[0].equals("move")) {
            throw new IllegalArgumentException("해당 명령이 존재하지 않습니다.");
        }
    }
}
