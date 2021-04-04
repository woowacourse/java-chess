package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String BLANK = " ";

    public static String askCommand() {
        return SCANNER.next();
    }

    public static String[] askPositions() {
        return SCANNER.nextLine()
                      .split(BLANK);
    }
}
