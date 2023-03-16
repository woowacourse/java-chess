package techcourse.fp.chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readInitCommand() {
        return scanner.nextLine().trim();
    }

    public String[] readCommand() {
        final String input = scanner.nextLine();
        return input.split(" ");
    }
}
