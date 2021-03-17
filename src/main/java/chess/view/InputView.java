package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private String receiveInputWithMessage(String message) {
        System.out.println(message);
        return SCANNER.nextLine().trim();
    }

    private String receiveInput() {
        return SCANNER.nextLine().trim();
    }
}
