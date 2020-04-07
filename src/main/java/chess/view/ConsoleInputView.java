package chess.view;

import java.util.Scanner;

public class ConsoleInputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String requestCommand() {
        return SCANNER.next();
    }

    public static String requestPosition() {
        return SCANNER.next();
    }
}
