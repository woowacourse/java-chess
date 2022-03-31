package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static CommandDto requestCommand() {
        try {
            return getCommand();
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return requestCommand();
        }
    }

    private static CommandDto getCommand() {
        final String input = SCANNER.nextLine();
        final String text = input.toLowerCase();
        return CommandDto.from(text);
    }
}
