package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Command readCommand() {
        String command = scanner.next();
        return Command.from(command);
    }

    public static String readSquare() {
        return scanner.next();
    }
}
