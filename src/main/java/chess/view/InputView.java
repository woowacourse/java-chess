package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

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
        if (!"move".equals(splitMoveCommand.get(0))) {
            throw new IllegalArgumentException("허용되지 않은 명령입니다.");
        }
        return splitMoveCommand.subList(1,3);
    }

    private static boolean validateStartOrEnd(final String userInput) {
        if ("start".equals(userInput)) {
            return true;
        }
        if ("end".equals(userInput)) {
            return false;
        }
        throw new IllegalArgumentException("허용되지 않은 명령입니다.");
    }
}
