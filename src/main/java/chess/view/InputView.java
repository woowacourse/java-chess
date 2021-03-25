package chess.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static chess.controller.ChessController.MOVE;
import static chess.controller.ChessController.STATUS;

public class InputView {
    public static final String INVALID_INPUT_ERROR_MESSAGE = "올바르지 않은 입력입니다.";
    public static final String END = "end";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static boolean inputStart() {
        String startInput = scanner.nextLine();
        if (startInput.equals(OutputView.START)) {
            return true;
        }
        if (startInput.equals(OutputView.END)) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
    }

    public static List<String> inputEachTurn() {
        String input = scanner.nextLine();
        if (STATUS.equals(input)) {
            return Collections.singletonList(STATUS);
        }

        if (END.equals(input)) {
            System.exit(0);
        }

        List<String> inputMoving = Arrays.asList(input.split(" "));
        if (isUnsupportedOperation(inputMoving)) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
        return inputMoving;
    }

    private static boolean isUnsupportedOperation(List<String> inputMoving) {
        return !MOVE.equals(inputMoving.get(0));
    }
}
