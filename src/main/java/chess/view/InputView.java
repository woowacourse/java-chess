package chess.view;

import java.util.Scanner;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputCommand() {
        return scanner.nextLine();
    }
}
