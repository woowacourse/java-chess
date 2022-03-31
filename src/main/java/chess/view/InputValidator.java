package chess.view;

import java.util.Arrays;
import java.util.List;

public class InputValidator {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String COMMAND_MATCH_ERROR = ERROR_MESSAGE + "명령어는 start, end, move, status 로 입력해야 합니다.";
    private static final String WRONG_LENGTH_ERROR = ERROR_MESSAGE + "움직일 기물의 위치는 [알파벳 숫지] 2글자로 입력해야 합니다.";

    public static void validateCommand(final String answer) {
        List<String> input = Arrays.asList(answer.split(" "));
        String command = input.get(0);
        if (command.equalsIgnoreCase(START) || command.equalsIgnoreCase(END) || command.equalsIgnoreCase(STATUS)) {
            return;
        }
        if (command.equalsIgnoreCase(MOVE) && input.size() == 3) {
            validateLength(input);
            return;
        }

        throw new IllegalArgumentException(COMMAND_MATCH_ERROR);
    }

    private static void validateLength(final List<String> input) {
        String sourcePosition = input.get(1);
        String targetPosition = input.get(2);
        if (sourcePosition.length() != 2 || targetPosition.length() != 2) {
            throw new IllegalArgumentException(WRONG_LENGTH_ERROR);
        }
    }
}
