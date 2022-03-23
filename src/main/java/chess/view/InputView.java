package chess.view;

import chess.dto.MovePositionCommandDto;
import java.util.Scanner;

public class InputView {

    private static final String START = "start";
    private static final String END = "end";
    private static final String INVALID_START_OR_END_INPUT_EXCEPTION_MESSAGE = "start 혹은 end만 입력하셔야 합니다.";
    private static final int EXIT_STATUS_CODE = 0;

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean requestStartOrEndInput() {
        String input = scanner.nextLine();
        validateStartOrEnd(input);
        return input.equals(START);
    }

    private static void validateStartOrEnd(String input) {
        if (!input.equals(START) && !input.equals(END)) {
            throw new IllegalArgumentException(INVALID_START_OR_END_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public static MovePositionCommandDto requestMoveOrEndInput() {
        String input = scanner.nextLine();
        if (input.equals(END)) {
            System.exit(EXIT_STATUS_CODE);
        }
        return new MovePositionCommandDto(input);
    }
}
