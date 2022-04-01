package chess.view;

import java.util.Scanner;

public class InputView {

    private InputView() {
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static String input() {
        return scanner.nextLine();
    }
}
