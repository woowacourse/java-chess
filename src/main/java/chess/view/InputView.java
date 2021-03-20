package chess.view;

import java.util.Scanner;

public class InputView {

    public static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    // TODO : need null, empty checking
    public static String inputCommand() {
        return SCANNER.nextLine().trim();
    }
}
