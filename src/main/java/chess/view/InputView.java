package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final int THREE_WORDS = 3;
    private static final int ONE_WORD = 1;

    private InputView() {
    }

    public static boolean inputChessStartOrEnd() {
        try {
            final String startOrEndInput = scanner.nextLine();
            return validateStartOrEnd(startOrEndInput);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputChessStartOrEnd();
        }
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

    public static List<String> inputTurnOption() {
        try {
            final String userTurnInput = scanner.nextLine();
            final String[] turnOptionInput = userTurnInput.split(" ");
            return validateTurnOption(turnOptionInput);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputTurnOption();
        }
    }

    private static List<String> validateTurnOption(final String[] turnOptionInput) {
        if (MOVE_COMMAND.equals(turnOptionInput[0]) && turnOptionInput.length == THREE_WORDS) {
            return trimStringArray(turnOptionInput);
        }
        if (STATUS_COMMAND.equals(turnOptionInput[0]) && turnOptionInput.length == ONE_WORD) {
            return trimStringArray(turnOptionInput);
        }
        if (END_COMMAND.equals(turnOptionInput[0]) && turnOptionInput.length == ONE_WORD) {
            return trimStringArray(turnOptionInput);
        }
        throw new IllegalArgumentException("허용되지 않은 명령입니다.");
    }

    private static List<String> trimStringArray(final String[] stringArray) {
        return Arrays.stream(stringArray)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
