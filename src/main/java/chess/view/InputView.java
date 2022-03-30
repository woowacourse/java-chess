package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final int MOVE_COMMAND_INDEX = 0;
    public static final int FIRST_POSITION_INDEX = 1;
    public static final int SECOND_POSITION_INDEX = 2;

    public static List<String> requestCommand() throws IllegalStateException {
        String input = SCANNER.nextLine();
        List<String> inputValues = List.of(input.split(" "));
        validateInputPosition(inputValues);
        return List.of(input.split(" "));
    }

    private static void validateInputPosition(List<String> inputValues) throws IllegalStateException {
        if (inputValues.get(MOVE_COMMAND_INDEX).equals("move")) {
            validatePositionLength(inputValues.get(FIRST_POSITION_INDEX));
            validatePositionLength(inputValues.get(SECOND_POSITION_INDEX));
        }
    }

    private static void validatePositionLength(String input) {
        if (input.length() != 2) {
            throw new IllegalStateException("입력 형식이 올바르지 않습니다.");
        }
    }
}
