package chess.validation;

import java.util.Arrays;
import java.util.List;

public class Validation {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int MOVE_COMMAND_INDEX = 0;

    private static final String BLANK = " ";
    private static final String MOVE_COMMAND = "move";


    public static List<String> validateInputMoveCommand(String input) {
        List<String> inputValues = Arrays.asList(input.split(BLANK));
        if (isIncorrectCommandLength(inputValues)) {
            throw new IllegalArgumentException(input + "커맨드는 move , 움직이고싶은말의 좌표 , 움직일좌표 형식에 맞지 않습니다.");
        }

        if (isNotMoveCommand(inputValues)) {
            throw new IllegalArgumentException(input + "커맨드는 move가 정확히 입려되지 않았습니다.");
        }

        if (isSourceEqualTarget(inputValues)) {
            throw new IllegalArgumentException(input + "커맨드는 움직이고싶은말의 좌표, 움직일좌표가 동일합니다. ");
        }
        return extractMovePosition(inputValues);
    }

    private static boolean isIncorrectCommandLength(List<String> inputValues) {
        return inputValues.size() != MOVE_COMMAND_SIZE;
    }

    private static boolean isNotMoveCommand(List<String> inputValues) {
        return !MOVE_COMMAND.equals(inputValues.get(MOVE_COMMAND_INDEX));
    }

    private static boolean isSourceEqualTarget(List<String> inputValues) {
        return inputValues.get(SOURCE_INDEX).equals(inputValues.get(TARGET_INDEX));
    }

    private static List<String> extractMovePosition(List<String> inputValues) {
        return Arrays.asList(inputValues.get(SOURCE_INDEX), inputValues.get(TARGET_INDEX));
    }
}
