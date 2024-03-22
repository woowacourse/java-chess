package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public Menu readMenu() {
        return Menu.fromInput(SCANNER.next());
    }

    public String readMoveCommand() {
        return SCANNER.next();
    }
}
