package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    private InputView() {
    }

    public static boolean inputChessStartOrEnd() {
        try {
            final String userInput = scanner.nextLine();
            return validateStartOrEnd(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputChessStartOrEnd();
        }
    }

    public static List<String> inputMovePosition() {
        final String inputMoveCommand = scanner.nextLine();
        List<String> splitMoveCommand = Arrays.asList(inputMoveCommand.split(" "));
        if (MOVE_COMMAND.equals(splitMoveCommand.get(0)) || STATUS_COMMAND.equals(splitMoveCommand.get(0))) {
            return splitMoveCommand;
        }
        throw new IllegalArgumentException("허용되지 않은 명령입니다.");
    }

    private static boolean validateStartOrEnd(final String userInput) {
        if (START_COMMAND.equals(userInput)) {
            return true;
        }
        if (END_COMMAND.equals(userInput)) {
            return false;
        }
        throw new IllegalArgumentException("허용되지 않은 명령입니다.");
    }
}
