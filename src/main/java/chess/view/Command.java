package chess.view;

import java.util.Arrays;
import java.util.Objects;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final String ERROR_MESSAGE = "잘못된 명령입니다.";
    private static final String SPLIT_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;
    private static final int MOVE_COMMAND_ARGUMENT_COUNT = 3;

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    static void validate(final String input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("null은 허용되지 않습니다.");
        }

        Arrays.stream(values())
                .filter(it -> input.startsWith(it.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    public static Command from(final String text) {
        final String[] splitText = text.split(SPLIT_DELIMITER);
        return Arrays.stream(values())
                .filter(it -> it.value.equals(splitText[COMMAND_INDEX]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    public static String getFromPosition(final String text) {
        final String[] splitText = text.split(SPLIT_DELIMITER);
        if (splitText.length != MOVE_COMMAND_ARGUMENT_COUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return splitText[FROM_POSITION_INDEX];
    }

    public static String getToPosition(final String text) {
        return text.split(SPLIT_DELIMITER)[TO_POSITION_INDEX];
    }
}
