package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readGameCommand() {
        return scanner.nextLine().trim();
    }
}
