package chess.console.view;

import java.util.Scanner;

public final class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputCommand() {
        return SCANNER.nextLine();
    }
}
