package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String getInput() {
        final String input = SCANNER.nextLine();
        return input.toLowerCase();
    }

    public static Command requestCommand() {
        try {
            return getCommand();
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return requestCommand();
        }
    }

    private static Command getCommand() {
        final String input = SCANNER.nextLine();
        final String text = input.toLowerCase();
        return Command.from(text);
    }
}
