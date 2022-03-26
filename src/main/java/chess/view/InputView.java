package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String requestCommand() {
        try {
            return getCommand();
        } catch (final IllegalArgumentException e) {
            System.out.println("[ERROR] : " + e.getMessage());
            return requestCommand();
        }
    }

    private static String getCommand() {
        final String input = SCANNER.nextLine();
        final String text = input.toLowerCase();

        Command.validate(text);

        return text;
    }
}
