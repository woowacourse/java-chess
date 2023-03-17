package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Command readCommand() {
        try {
            String command = scanner.next();
            return Command.from(command);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand();
        }
    }

    public static String readSquare() {
        return scanner.next();
    }

    public static String terminate() {
        scanner.close();
    }
}
