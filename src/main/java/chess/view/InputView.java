package chess.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static chess.controller.ChessController.MOVE;
import static chess.controller.ChessController.STATUS;

public class InputView {
    public static final String INVALID_INPUT_ERROR_MESSAGE = "올바르지 않은 입력입니다.";
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

    public static List<String> inputMoveOrStatus() {
        String input = scanner.nextLine();
        if (input.equals(STATUS)) {
            return Collections.singletonList(STATUS);
        }

        List<String> inputMoving = Arrays.asList(input.split(" "));
        if (!inputMoving.get(0).equals(MOVE)) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
        return inputMoving;
    }
}
