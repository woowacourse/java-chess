package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static Command readCommand() {
        String input = scanner.nextLine();
        try {
            return Command.of(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readCommand();
        }
    }
}
