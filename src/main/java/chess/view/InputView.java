package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readCommand() {
        final String input = scanner.nextLine();
        Command.validateFormat(input);
        return input;
    }
}
