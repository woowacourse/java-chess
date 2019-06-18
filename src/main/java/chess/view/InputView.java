package chess.view;

import java.util.Scanner;

public class InputView {
    public static Scanner SCANNER = new Scanner(System.in);

    public static String command() {
        return SCANNER.nextLine();
    }
}
