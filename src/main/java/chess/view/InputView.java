package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public InputTokens readCommand() {
        String command = scanner.nextLine().trim();
        return new InputTokens(command);
    }
}
