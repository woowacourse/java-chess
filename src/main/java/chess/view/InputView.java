package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ERROR_PREFIX = "[ERROR] : ";

    public static String inputCommand() {
        try {
            return SCANNER.nextLine();
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return inputCommand();
        }
    }
}
