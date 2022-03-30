package chess.view;

import java.util.Scanner;

public class InputView {

    private static final String EMPTY_REGEX = " ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String[] inputMenu() {
        try {
            return scanner.nextLine().split(EMPTY_REGEX);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMenu();
        }
    }
}
