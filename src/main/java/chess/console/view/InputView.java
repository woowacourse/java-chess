package chess.console.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final int COMMAND_INDEX = 0;
    public static final int FROM_POSITION_INDEX = 1;
    public static final int TO_POSITION_INDEX = 2;
    private static final int COMMAND_MIN_COUNT = 1;
    private static final int COMMAND_MAX_COUNT = 3;
    private static final String SEPERATOR = " ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputCommand() {
        try {
            List<String> inputs = List.of(scanner.nextLine().split(SEPERATOR, -1));
            validInputs(inputs);
            return inputs;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
