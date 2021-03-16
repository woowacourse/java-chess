package chess.view;

import java.util.Scanner;

public class InputView {
    final static Scanner scanner = new Scanner(System.in);

    public static String command() {
        return scanner.nextLine();
    }
}
