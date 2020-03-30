package chess.view2;

import java.util.Scanner;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);

    public static String inputState() {
        return SCANNER.nextLine().trim();
    }
}
