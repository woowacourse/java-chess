package chess.View;

import java.util.Scanner;

public class InputView {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static String inputCommand() {
        return SCANNER.nextLine();
    }
}
