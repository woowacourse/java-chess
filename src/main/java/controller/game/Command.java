package controller.game;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    CANCEL("cancel"),
    EMPTY("");

    public static final int COMMAND_INDEX = 0;
    public static final int CURRENT_SQUARE_INDEX = 1;
    public static final int TARGET_SQUARE_INDEX = 2;
    public static final int STANDARD_COMMAND_LENGTH = 1;
    public static final int MOVE_COMMAND_LENGTH = 3;
    public static final String COMMAND_ERROR_MESSAGE = "입력이 올바르지 않습니다.";


    private final String value;

    Command(String value) {
        this.value = value;
    }


    public static Command find(String command) {
        return Arrays.stream(Command.values())
                .filter(runCommand -> runCommand.value.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }

    public static void validateCommandLength(int given, int expected) {
        if (given != expected) {
            throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
        }
    }
}
