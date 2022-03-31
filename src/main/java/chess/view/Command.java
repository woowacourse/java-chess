package chess.view;

import chess.domain.position.Position;
import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    STATUS("status"),
    MOVE("move"),
    ;

    private static final String ERROR_MESSAGE = "잘못된 명령입니다.";
    private static final String MOVE_COMMAND_FORMAT_ERROR = "move source위치 target위치 형식이 아닙니다.";
    private static final String SPLIT_DELIMITER = " ";
    private static final int SPLIT_TEXT_LENGTH = 3;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command from(String text) {
        return Arrays.stream(values())
                .filter(it -> text.startsWith(it.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    public static Position findFromPosition(final String input) {
        final String[] splitText = input.split(SPLIT_DELIMITER);
        if (splitText.length != SPLIT_TEXT_LENGTH) {
            throw new IllegalArgumentException(MOVE_COMMAND_FORMAT_ERROR);
        }
        return Position.from(splitText[FROM_POSITION_INDEX]);
    }

    public static Position findToPosition(final String input) {
        final String[] splitText = input.split(SPLIT_DELIMITER);
        if (splitText.length != SPLIT_TEXT_LENGTH) {
            throw new IllegalArgumentException(MOVE_COMMAND_FORMAT_ERROR);
        }
        return Position.from(splitText[TO_POSITION_INDEX]);
    }
}
