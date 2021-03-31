package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String receiveInputWithMessage(String message) {
        System.out.println(message);
        return SCANNER.nextLine().trim();
    }

    public static String receiveInput() {
        return SCANNER.nextLine().trim();
    }
}
