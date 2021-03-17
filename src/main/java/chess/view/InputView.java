package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String INVALID_INPUT_ERROR_MESSAGE = "올바르지 않은 입력입니다.";

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
}
