package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public boolean isCommandStart() {
        String input = scanner.nextLine();
        Command command = Command.from(input);
        return command.isStart();
    }
}
