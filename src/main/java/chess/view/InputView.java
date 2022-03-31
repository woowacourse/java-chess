package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public String inputCommand() {
        return SCANNER.nextLine();
    }
}
