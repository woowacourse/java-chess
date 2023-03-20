package chess.view;

import java.util.Scanner;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Command readCommand() {
        return Command.from(SCANNER.next());
    }

    public static String getCoordinate() {
        return SCANNER.next();
    }
}

