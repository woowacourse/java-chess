package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static Menu inputMenu() {
        try {
            String input = scanner.nextLine();
            return Menu.of(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMenu();
        }
    }
}
