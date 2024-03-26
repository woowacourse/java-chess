package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public CommandExpression readCommand() {
        String rawInput = scanner.nextLine();

        return CommandExpression.of(rawInput);
    }
}
