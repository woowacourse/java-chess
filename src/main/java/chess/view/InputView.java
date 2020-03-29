package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SPACE = " ";

    public static String[] inputCommand() {
        try {
            return SCANNER.nextLine().split(SPACE);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputCommand();
        }
    }
}
