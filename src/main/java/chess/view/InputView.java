package chess.view;

import chess.command.Command;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
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
        return CommandMapper.from(text);
    }
}
