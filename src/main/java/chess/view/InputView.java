package chess.view;

import static chess.controller.ChessController.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String SPACE = " ";

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
        throw new IllegalArgumentException("올바르지 않은 입력입니다.");
    }

    public static List<String> inputMoveOrStatus() {
        String input = scanner.nextLine();
        if (input.equals(STATUS)) {
            return Collections.singletonList(STATUS);
        }

        List<String> inputMoving = Arrays.asList(input.split(SPACE));
        if (inputMoving.get(0).equals(MOVE)) {
            return inputMoving;
        }

        if (inputMoving.get(0).equals(START) || inputMoving.get(0).equals(END)) {
            return inputMoving;
        }
        throw new IllegalArgumentException("옳지 않은 입력입니다.");
    }
}
