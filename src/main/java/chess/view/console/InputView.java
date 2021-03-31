package chess.view.console;

import java.util.Scanner;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);

    public static String readUserInput() {
        System.out.print("> ");
        return SCANNER.nextLine();
    }
}
