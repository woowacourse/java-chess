package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final String ERROR_MESSAGE = "잘못된 명령입니다.";
    private static final String DELIMITER = " ";
    private static final int COMMAND = 0;
    private static final int MOVE_COMMAND_INFO = 3;
    private static final int FROM_POSITION = 1;
    private static final int TO_POSITION = 2;

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static void validateCommand(String input) {
        Arrays.stream(values())
                .filter(it -> input.startsWith(it.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    public static Command splitCommand(String text) {
        String[] splitText = text.split(DELIMITER);
        return Arrays.stream(values())
                .filter(it -> it.value.equals(splitText[COMMAND]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    public static String getFromPosition(String text) {
        String[] splitText = text.split(DELIMITER);
        validateMoveCommandFormat(splitText);
        return splitText[FROM_POSITION];
    }

    private static void validateMoveCommandFormat(String[] splitText) {
        if (splitText.length != MOVE_COMMAND_INFO) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public static String getToPosition(String text) {
        return text.split(DELIMITER)[Command.TO_POSITION];
    }
}
