package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public static final String INVALID_INPUT_ERROR_MESSAGE = "올바르지 않은 입력입니다.";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MOVE = "move";

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

    public static List<String> inputMovingPiece() {
        List<String> inputMoving = Arrays.asList(scanner.nextLine().split(" "));

        if (!inputMoving.get(0).equals(INPUT_MOVE)) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }

        return inputMoving.stream()
                .skip(1)
                .collect(Collectors.toList());
    }
}
