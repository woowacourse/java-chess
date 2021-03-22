package chess.view;

import java.util.Scanner;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String command() {
        return SCANNER.nextLine();
    }
}
