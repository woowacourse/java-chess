package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readStartCommand() {
        OutputView.printStartInformation();
        return SCANNER.nextLine();
    }

    public static String read() {
        return SCANNER.nextLine();
    }
}
