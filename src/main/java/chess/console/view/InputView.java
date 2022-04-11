package chess.console.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final int COMMAND_MIN_COUNT = 1;
    private static final int COMMAND_MAX_COUNT = 3;
    private static final String SEPARATOR = " ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputCommand() {
        try {
            List<String> inputs = List.of(scanner.nextLine().split(SEPARATOR, -1));
            validInputs(inputs);
            return inputs;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputCommand();
        }
    }

    private static void validInputs(List<String> inputs) {
        int size = inputs.size();
        if (size != COMMAND_MIN_COUNT && size != COMMAND_MAX_COUNT) {
            throw new IllegalArgumentException("잘못된 명령어를 입력하셨습니다.");
        }
    }
}
