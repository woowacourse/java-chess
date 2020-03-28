package chess.view;

import java.util.Scanner;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);

    public static String inputGameState() {
        return SCANNER.nextLine();
    }
}
